//package cn.bravolinks.erp.crm.server.util;
//
///**
// * Created by yanqin on 2017/2/18.
// */
//public class GetShenpirenUtils {
//
//    public static String handleResult(String s){
//        if(s.indexOf(",")>=0){
//            String[] arr = s.split(",");
//            StringBuilder sb = new StringBuilder();
//            for(String temp : arr){
//                sb.append(temp+" ");
//            }
//            sb.delete(sb.length()-1,sb.length());
//            return sb.toString();
//        }else{
//            return s;
//        }
//    }
//
//    /**
//     * 根据部门id和角色id查询账户
//     * @return
//     */
//    public static String getUseridDaoByDeptIDAndRoleid(Integer roleid,Integer deptId){
//        String url = PropertiesUtils.getPropertyByCommon("GetShenpiren") + "getUseridDaoByDeptIDAndRoleid?roleid="+roleid+"&deptId="+deptId;
//        return HttpUtils.httpGet(url);
//    }
//
//    /**
//     * 是不是北京公司的人
//     * @param userid
//     * @return
//     */
//    public static boolean getIsBJGS(String userid){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowServiceURL") + "custom/getIsBJGS?userid="+userid;
//        return HttpUtils.httpGet(url).equals("true")?true:false;
//    }
//
//    /**
//     * 查大客户总监
//     * @return
//     */
//    public static String getDKHZJ(String khbh){
//        String url = PropertiesUtils.getPropertyByCommon("GetShenpiren") + "getDKHZJ?khbh="+khbh;
//        return HttpUtils.httpGet(url);
//    }
//}
