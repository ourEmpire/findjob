package controller;

import message.Messages.Message;
import message.Messages;
import message.StateCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequestMapping("/users")
@RestController
public class FileUploadCtl implements Ctl {

    private Message[] messages = new Messages().getMessages();
    @CrossOrigin
    @PostMapping("/{id}")
    public Message fileUpload(@PathVariable String id,
                            @RequestParam("file") MultipartFile file){
        //设置基地址
        StringBuffer base = new StringBuffer("E:/repo");
        StringBuffer retStr = new StringBuffer("/users");
        Message message = messages[Messages.POST];
        if(file.isEmpty()){
            return message.setCode(StateCode.NO_CONTENT)
                            .setMsg("the file param is null")
                            .setData(null);
        }
        String fileName = file.getOriginalFilename();
        base.append(retStr.append("/").append(id));
        File userHome = new File(base.toString());
        if(!userHome.exists()){
            userHome.mkdir();
        }
        retStr.append("/").append(fileName);
        File dest = new File(userHome,fileName);
        try {
            file.transferTo(dest); //保存文件
            message.setCode(StateCode.CREATED)
                    .setMsg("file upload successfully")
                    .setData(retStr);
            return message;
        } catch (IOException e) {
            message.setCode(StateCode.INTERNAL_SERVER_ERROR)
                    .setMsg(e.getMessage())
                    .setData(null);
            return message;
        }
    }
}
