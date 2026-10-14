package net.shangye.manage.test.mobilepush.service.impl;

import net.shangye.manage.test.mobilepush.push_api.dto.PushMessage;
import net.shangye.manage.test.mobilepush.push_api.dto.ResultMessage;
import net.shangye.manage.test.mobilepush.service.PushMessageService;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PushMessageServiceImpl  implements PushMessageService {


    @Override
    public List<PushMessage> getAllPushMessages() {
        return null;
    }

    @Override
    public void pushMessage(List<PushMessage> messages) {

    }

    @Override
    public void updateMessage(List<PushMessage> messages, List<ResultMessage> resultMessages) {

    }

    @Override
    public JSONObject readMessageJson() {
        return null;
    }

    @Override
    public void writeMessageJson(JSONObject msgJson) {

    }
}
