package com.example.shop.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    // application.properties에 지정한 값을 가져올 수 있는 어노테이션
    @Value("${item.file.dir}")
    private String itemFileDir;
    
    // item 파일을 실제 서버에 저장
    public String itemFileUpload(MultipartFile itemFile) throws Exception{

        // 실제로 서버에 저장된 고유값을 가진 파일명
        String realFileName = createFileName(itemFile.getOriginalFilename());

        // 폴더 만들기
        File dir = new File(itemFileDir);

        // 해당 경로에 디렉토리나 파일이 존재하지 않는다면
        if(!dir.exists()){
            // 해당 경로로 디렉토리 생성
            if (dir.mkdirs()) {
                System.out.println("디렉토리 생성 완료: " + itemFileDir);
            } else {
                System.out.println("디렉토리 생성 실패: " + itemFileDir);
            }
        }
        
        // 파일을 우선 서버에 저장
        itemFile.transferTo(new File(itemFileDir + File.separator + realFileName));
        
        return "\\images\\itemImages" + File.separator + realFileName;
    }
    
    // 파일 명 생성 함수
    public String createFileName(String fileName){
        
        // 파일명이 혹시 동일 할 경우를 대비해 고유값을 만듦
        String uuid = UUID.randomUUID().toString();
        // 확장자 가져오기
        String ext = fileName.substring(fileName.indexOf("."));

        return uuid + ext;
    }
}
