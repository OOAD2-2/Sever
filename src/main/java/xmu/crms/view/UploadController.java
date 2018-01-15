package xmu.crms.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.User;
import xmu.crms.exception.*;
import xmu.crms.mapper.UserMapper;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.UploadService;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author LUWEIW
 */

@Controller
public class UploadController {

    @Autowired
    private ResourceLoader resourceLoader;

    //@Autowired
    //UploadService uploadService;

    @Autowired
    private SeminarGroupService seminarGroupService;

    private String baseDir = "D:" + System.getProperty("file.separator");

    @PostMapping("/upload/avatar")
    public Object uploadAvatar(@RequestParam MultipartFile avatar) {

        String curTime = String.valueOf(System.currentTimeMillis());
        String filename = Base64.getEncoder().encodeToString(curTime.getBytes());
        File file = new File(baseDir + filename);
        try {
            avatar.transferTo(file);
            return new Object() {
                public String file = filename;
            };
        } catch (Exception e) {
            return false;
        }
    }

    //@PostMapping("/upload/report")
    //public void uploadReport(@RequestParam("file") MultipartFile file, @RequestParam("seminarId") String seminarId,
    //                         @RequestAttribute("userId") String userId) throws GroupNotFoundException, FileNotFoundException, IOException {
    //    //判断文件是否为空
    //    if (file == null) {
    //        throw new FileNotFoundException();
    //    }
    //    String fileName=file.getOriginalFilename();
    //    String curTime = String.valueOf(System.currentTimeMillis());
    //    String tempFilename = Base64.getEncoder().encodeToString(curTime.getBytes());
    //    File tempFile = new File(baseDir + tempFilename);
    //    tempFile.createNewFile();
    //    SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupById(new BigInteger(seminarId), new BigInteger(userId));
    //    seminarGroup.setReport(baseDir + tempFile.getName());
    //    uploadService.uploadReport(seminarGroup);
    //}

    //@PostMapping("/upload/studentList")
    //@ResponseStatus(HttpStatus.OK)
    //public void uploadStudentList(@RequestParam("classId") String classId, @RequestParam("file") MultipartFile file,
    //                              @RequestAttribute("userId") String userId) throws ClassesNotFoundException, UserNotFoundException {
    //    System.out.println(classId);
    //    uploadService.uploadStudentList(file, new BigInteger(classId), new BigInteger(userId));
    //}

    @GetMapping("/avatar/{avatar}")
    public HttpEntity avatar(@PathVariable("avatar") String avatar) {
        File file = new File(baseDir + avatar);
        if (file.exists() == false) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                    .body(resourceLoader.getResource("file:" + baseDir + avatar));
        }
    }

    @GetMapping("/download/report")
    @ResponseStatus(HttpStatus.OK)
    public void downloadReport(@RequestParam("url") String report, HttpServletResponse response) throws FileNotFoundException {
        System.out.println(report);
        File file = new File(report);
        if (file == null) {
            System.out.println("haha");
            throw new FileNotFoundException();
        }
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());

        OutputStream outputStream = null;
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        try {
            outputStream = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                System.out.println(buff);
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != bis) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
