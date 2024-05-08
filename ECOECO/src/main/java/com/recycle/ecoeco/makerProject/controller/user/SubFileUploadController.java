package com.recycle.ecoeco.makerProject.controller.user;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
//@RequestMapping("/user/sub")
public class SubFileUploadController {

//    @PostMapping("/summary"){
//
//    }

    @PostMapping("/single-file")
    public String singleFileUpload(@RequestParam String singleFileDescription,
                                   @RequestParam MultipartFile singleFile,
                                   Model model) {

        System.out.println("singleFileDescription : " + singleFileDescription);
        System.out.println("singleFile : " + singleFile);

        /* 서버로 전달된 File을 서버에서 설정하는 경로에 저장한다. */
        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);
        System.out.println(dir.getAbsolutePath());

        if(!dir.exists()) dir.mkdir();

        /* 파일명 변경 처리 */
        String originFileName = singleFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        String savedName = UUID.randomUUID() + ext;
        String fileFullPath = Paths.get(filePath, savedName).toString();

        /* 파일 저장 */
        try {
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message", "파일 업로드 완료!");
            System.out.println("파일 업로드 완료!");
        } catch (IOException e) {
            model.addAttribute("message", "파일 업로드 실패!");
            System.out.println("파일 업로드 실패!!!");
            System.out.println(fileFullPath);
        }
        return "result";
    }

    /*
     * 에디터 이미지 업로드
     * @param image 파일 객체
     * @return 업로드된 파일명
     */
    @PostMapping("/editer-image-upload")
    public String uploadEditorImage(@RequestParam MultipartFile image) {

        if (image.isEmpty()) {
            return "";
        }

        System.out.println("image : " + image);

        /* 서버로 전달된 File을 서버에서 설정하는 경로에 저장한다. */
        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);
        System.out.println(dir.getAbsolutePath());

        if(!dir.exists()) dir.mkdir();

        /* 파일명 변경 처리 */
        String originFileName = image.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        String savedName = UUID.randomUUID() + ext;
        String fileFullPath = Paths.get(filePath, savedName).toString();

        /* 파일 저장 */
        try {
            image.transferTo(new File(filePath + "/" + savedName));
//            model.addAttribute("message", "파일 업로드 완료!");
            System.out.println("파일 업로드 완료!");
        } catch (IOException e) {
//            model.addAttribute("message", "파일 업로드 실패!");
            System.out.println("파일 업로드 실패!");
            System.out.println(fileFullPath);
        }
//        return "result";
        return savedName;
    }

    @GetMapping(value = "/editer-image-print", produces = { MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    public byte[] printEditorImage(@RequestParam final String filename) {

        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        String fileFullPath = Paths.get(filePath, filename).toString();

        // 파일이 없는 경우 예외 throw
        File uploadedFile = new File(fileFullPath);
        if (!uploadedFile.exists()) {
            throw new RuntimeException();
        }

        try {
            // 이미지 파일을 byte[]로 변환 후 반환
            byte[] imageBytes = Files.readAllBytes(uploadedFile.toPath());
            return imageBytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
