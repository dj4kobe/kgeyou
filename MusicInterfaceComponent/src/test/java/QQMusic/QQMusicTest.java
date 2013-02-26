package QQMusic;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: liscxx
 * Date: 13-2-26
 * Time: 下午9:11
 * To change this template use File | Settings | File Templates.
 */
public class QQMusicTest {
    public static void main(String[] agrus) {
        String url = "";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try {
            String singer = URLEncoder.encode("", "GBK");
            String songName = URLEncoder.encode("蜗牛", "GBK");
            url = "http://shopcgi.qqmusic.qq.com/fcgi-bin/shopsearch.fcg?" +
                    "value=" + songName + "&artist=" + singer + "&type=qry_song&out=json&page_no=1&page_record_num=10";
            System.out.println("url:" + url);

            HttpGet httpGet = new HttpGet(url);
//            ResponseHandler<String> responseHandler = new BasicResponseHandler();
//            String responseBody = new String(httpClient.execute(httpGet, responseHandler).getBytes("UTF-8"), "GBK");
//            System.out.println("----------------------------------------");
//            System.out.println(responseBody);
//            System.out.println("----------------------------------------");


            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                byte[] bytes = new byte[1024];
                InputStream responseStream = httpEntity.getContent();
                InputStreamReader responseStreamReader = new InputStreamReader(responseStream, "GBK");
                BufferedReader bufferedReader = new BufferedReader(responseStreamReader);
                String lineContent = "";
                StringBuffer sb = new StringBuffer("");
                while((lineContent = bufferedReader.readLine()) != null){
                    sb.append(lineContent);
                }
                System.out.println("responseString:" + sb.toString());
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            httpClient.getConnectionManager().shutdown();
        }

    }
}
