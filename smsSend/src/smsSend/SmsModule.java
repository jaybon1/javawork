package	smsSend;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

// Message ��ü �����Ͽ� send �޼��� ����ϸ� ��
public class SmsModule {
  public SmsModule(String to, String text) {
    String api_key = "NCS25L956VYR1CEZ";
    String api_secret = "G86S4DO7X9LXZ05CXROFNFLWSVATX914";
    Message coolsms = new Message(api_key, api_secret);

    // 4 params(to, from, type, text) are mandatory. must be filled
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", to);
    params.put("from", "01099241840");
    params.put("type", "LMS");
    params.put("text", text);
    params.put("app_version", "test app 1.2"); // application name and version

    try {
      JSONObject obj = (JSONObject) coolsms.send(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
  }
  
}