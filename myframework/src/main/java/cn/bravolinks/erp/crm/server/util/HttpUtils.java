//package cn.bravolinks.erp.crm.server.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
///**
// * Created by yanqin on 2017/4/26.
// */
//public class HttpUtils {
//
//    private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
//
//    /**
//     * HttpPot
//     *
//     * @param url
//     * @param par
//     * @return
//     * @throws Exception
//     */
//    public static String httpPost(String url, String par) {
//        logger.info("url:" + url);
//        logger.info("par:" + par);
//
//        InputStream in = null;
//        BufferedReader rd = null;
//        try {
//            HttpURLConnection url_con = (HttpURLConnection) new URL(url).openConnection();
//            url_con.setRequestMethod("POST");
//            url_con.setDoOutput(true);
//            url_con.getOutputStream().write(par.getBytes());// 杈撳叆鍙傛暟
//            in = url_con.getInputStream();
//            rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
//            String tStr = rd.readLine();
//            url_con.disconnect();
//            return tStr;
//        } catch (Exception e) {
//            logger.error("httppost请求失败",e);
//            return null;
//        } finally {
//            try {
//                if (rd != null) {
//                    rd.close();
//                }
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException e) {
//                logger.error("httppost请求后关闭流失败",e);
//            }
//        }
//    }
//
//
//    /**
//     * HttpGet
//     *
//     * @param url
//     * @return
//     * @throws Exception
//     */
//    public static String httpGet(String url) {
//        logger.info("url:" + url);
//
//        InputStream in = null;
//        BufferedReader rd = null;
//        try {
//            HttpURLConnection url_con = (HttpURLConnection) new URL(url).openConnection();
//            url_con.setRequestMethod("GET");
//            url_con.setDoOutput(true);
//            in = url_con.getInputStream();
//            rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
//            String tStr = rd.readLine();
//            url_con.disconnect();
//            return tStr;
//        } catch (Exception e) {
//            logger.error("httpget请求失败",e);
//            return null;
//        } finally {
//            try {
//                if (rd != null) {
//                    rd.close();
//                }
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException e) {
//                logger.error("httpget请求后关闭流失败",e);
//            }
//        }
//    }
//
//}
