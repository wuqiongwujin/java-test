//package excel.handler.amount;
//
//import com.hupun.scm.common.util.UUIDGenerator;
//
//import excel.AbstractSpreadsheetRowsHandler;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Description 处理聚茂导入客户余额问题，扣减导入余额
// * @Author Cain
// * @date 2020/10/10
// */
//public class UseAmountHandler extends AbstractSpreadsheetRowsHandler {
//
//    public static final WeimobXinYunSession session = new DefaultWeimobXinYunSession("可以有会员商城(店铺)(店铺)", "c8df9962-2eb8-40be-b563-83b6d6edd4c5") ;
//
//    @Override
//    public void handleRows(Map<Integer, List<String>> rowsMap) {
//        for (Map.Entry<Integer,List<String>> entry : rowsMap.entrySet()) {
//            Integer row = entry.getKey();
//            List<String> params = entry.getValue();
//            if (row.equals(1)) {
//                continue;
//            } else {
//                String second = params.get(1);
//                if (second == null || second.length() == 0) continue;
//                Long wid = null;
//                try {
//                    wid = Long.valueOf(second);
//                } catch (Exception e) {
//                    System.out.println("客户编码错误,第"+ row +"行");
//                    continue;
//                }
//                if (wid == null) continue;
//                String third = params.get(3);
//                Double amount = null;
//                try {
//                    amount = Double.valueOf(third);
//                } catch (Exception e) {
//                    System.out.println("金额错误,第"+ row +"行");
//                    continue;
//                }
//                if (amount == null) continue;
//                String result = "";
//                try {
//                    if (useMemberPointAmountTest(wid, amount)) {
//                        result = "第"+row+"行处理成功";
//                        System.out.println(result);
//                    } else {
//                        result = "警告!第"+row+"行处理失败";
//                        System.out.println(result);
//                    }
//                } catch (WeimobXinYunException e) {
//                    if (e.getMsg().equals("会员余额不足")) {
//                        try {
//                            double customerAmount = getCustomerAmount(wid);
//                            if (customerAmount == 0) continue;
//                            if (useMemberPointAmountTest(wid, customerAmount)){
//                                result = "第"+row+"行余额扣减到0成功";
//                                System.out.println(result);
//                            } else {
//                                result = "第"+row+"行余额扣减到0失败";
//                                System.out.println(result);
//                            }
//                        } catch (WeimobXinYunException e1) {
//                            e1.printStackTrace();
//                            result = "有会员余额扣减到0出错,第"+row+"行";
//                            System.out.println(result);
//                        }
//                    } else {
//                        result = "第"+row+"行处理出错";
//                        System.out.println(result);
//                        e.printStackTrace();
//                    }
//                } catch (Exception e) {
//                    result = "第"+row+"行处理出错";
//                    System.out.println(result);
//                    e.printStackTrace();
//                }
//                wirte(result);
//            }
//        }
//    }
//
//    @Override
//    public void setTotalRowNum(int totalRowNum) {
//        System.out.println("文件总行数:"+totalRowNum);
//    }
//
//    /**
//     * 扣减会员余额
//     * @param wid
//     * @param amount
//     * @return
//     */
//    private boolean useMemberPointAmountTest(long wid, double amount) throws WeimobXinYunException {
//        MemberPointAmountUseRequest request = new MemberPointAmountUseRequest();
//        request.setWid(wid);
//        request.setPoint(null);
//        request.setAmount(BigDecimal.valueOf(amount));
//        request.setChannelType(MemberCardConsumeSceneEnum.SCENE_SUB_AMOUNT_OFFLINE.value);
//        request.setAttachId("JM:"+UUIDGenerator.generate());
//        request.setReason("扣减导入客户余额");
//        request.setRequestId(UUIDGenerator.generate());
//        return session.useMemberPointAmount(request);
//    }
//
//    /**
//     * 获取微盟客户余额
//     * @param wid
//     * @return
//     * @throws WeimobXinYunException
//     */
//    private double getCustomerAmount(long wid) throws WeimobXinYunException {
//        WeimobXinYunMember member = session.getMember(String.valueOf(wid));
//        if (member == null) throw new WeimobXinYunException(0, "会员不存在");
//        if (member.getAmount() == null) return 0;
//        return member.getAmount().doubleValue()/100;
//    }
//
//    private void wirte(String desc) {
//        File file = new File("/Users/wuqiong/Downloads/jmamount.txt");
//        if(!file.exists()){
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            FileOutputStream out = new FileOutputStream(file, true);
//            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
//            osw.write(desc);
//            osw.write("\r\n");
//            osw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
