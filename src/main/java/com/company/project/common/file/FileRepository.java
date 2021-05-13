package com.company.project.common.file;
import com.company.project.common.utils.DataResult;
import com.company.project.entity.SysFilesEntity;
import com.company.project.service.SysFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * @author ldh 2毛的文件上传...
 * @create 2020-10-12 9:38
 **/
@Repository
public class FileRepository{

    //@Value("${spring.commodityWeight.file.root.path}")
    @Value("${file.path}")
    private String fileRootPath;

    @Value("${file.url}")
    private String fileUrl;

    @Resource
    private SysFilesService sysFilesService;

    public DataResult uploadFile(MultipartFile file, HttpServletRequest request){
        if (file.isEmpty()){
            return DataResult.fail("上传的文件不能为空！请重新上传");
        }
        if (file.getSize()<=0){
            return DataResult.fail("上传的文件大小需要大于0kb");
        }
        Date date = new Date();
        Long time = date.getTime();
        //绝对路径
        String filePath =fileRootPath+ LocalDate.now() +"/";
        String originFileName = file.getOriginalFilename();
        String newFileName = time+originFileName;
        String newFilePath=filePath;
        File file1 = new File(newFilePath);
        if (!file1.exists()){
            file1.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(newFilePath+newFileName);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();

            String url=fileUrl+ "/"+LocalDate.now()+"/"+newFileName;

            //保存文件记录
            SysFilesEntity sysFilesEntity = new SysFilesEntity();
            sysFilesEntity.setFileName(newFileName);
            sysFilesEntity.setFilePath(filePath+newFileName);
            sysFilesEntity.setUrl(url);
            sysFilesEntity.setCreateDate(new Date());
            sysFilesService.save(sysFilesEntity);

            return DataResult.success(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DataResult.fail("上传失败");
    }

    public DataResult uploadFiles(List<MultipartFile> files) {
        List<String> errorList =new ArrayList<>();
        List<String> urlList=new ArrayList<>();
        //绝对路径
        String filePath =fileRootPath+ LocalDate.now() +"/";
        files.stream().forEach(f->{
            if (f.isEmpty()){
                errorList.add("文件名："+f.getName()+"为空;");
            }
            if (f.getSize()<=0){
                errorList.add("文件名："+f.getName()+"的大小需要大于0kb;");
            }
            Date date = new Date();
            Long time = date.getTime();
            String originFileName = f.getOriginalFilename();
            String newFileName = time+originFileName;
            String newFilePath=filePath;
            File file1 = new File(newFilePath);
            if (!file1.exists()){
                file1.mkdirs();
            }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(newFilePath+newFileName);
                fileOutputStream.write(f.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
                urlList.add(filePath+newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            errorList.add("文件名："+f.getName()+"上传失败;");
        });
        if(errorList.size()>0){
            return DataResult.fail(errorList.toString());
        }else{
            return DataResult.success(urlList);
        }
    }

    public void downloadFile(String filePath, HttpServletResponse response) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset();

        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());

        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }
}
