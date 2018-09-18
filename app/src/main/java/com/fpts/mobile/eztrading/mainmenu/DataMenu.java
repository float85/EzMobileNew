package com.fpts.mobile.eztrading.mainmenu;

import android.content.Context;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;
import com.fpts.mobile.eztrading.common.LanguageApp;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataMenu {
    private final static String FileMenuFavorite = "FileMenuFavorite";

    private static ArrayList<Integer> arrayListType = new ArrayList<>();
    private static ArrayList<String> arrayListTitle = new ArrayList<>();
    private static ArrayList<String> arrayListSaved = new ArrayList<>();

    private static ArrayList<String> arrayListFavorite = new ArrayList<>();
    private static ArrayList<Integer> arrayListTypeFavorite = new ArrayList<>();

    public static void init(Context context) {
        initPrivate(context);
    }

    private static void initPrivate(Context context) {
        String str = FileInputAndOutputStream.readData(context, FileMenuFavorite).replace("\n", "");
        arrayListType = getArrayListTypePrivate();
        if (LanguageApp.getLanguage(context) == LanguageApp.LANGUAGE_VI) {
            arrayListTitle = getArrayListTitlePrivate();
        } else arrayListTitle = getArrayListTitlePrivateEN();

        if (str == null || str.trim().equalsIgnoreCase("")) {
            ArrayList<String> listTitle = getArrayListTitlePrivate();
            ArrayList<String> listTitleEN = getArrayListTitlePrivateEN();
            arrayListSaved = new ArrayList<>();
            String data = "";
            for (int i = 0; i < listTitle.size(); i++) {
                data += listTitle.get(i) + "," + listTitleEN.get(i) + "," + arrayListType.get(i) + ",0@";
                arrayListSaved.add(i, "0");
            }
            FileInputAndOutputStream.saveData(context, data, FileMenuFavorite);
        } else {
            String[] data = str.split("@");
            arrayListSaved = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                String[] line = data[i].split(",");
                if (line.length > 3)
                    arrayListSaved.add(data[i].split(",")[3]);
            }
        }
    }

    public static ArrayList<String> getArrayListSaved() {
        return arrayListSaved;
    }

    public static ArrayList<String> getUserInfo(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        //
        arrayList.add("058C123456");
        arrayList.add("Nguyễn Văn A");

        return arrayList;
    }

    public static ArrayList<String> getArrayListFavorite(Context context) {
        return getArrayListFavoritePrivate(context);
    }

    public static ArrayList<Integer> getArrayListTypeFavorite() {
        return arrayListTypeFavorite;
    }

    private static ArrayList<String> getArrayListFavoritePrivate(Context context) {
        arrayListFavorite = new ArrayList<>();
        arrayListTypeFavorite = new ArrayList<>();

        String s = FileInputAndOutputStream.readData(context, FileMenuFavorite);
        if (s == null || s.trim().equalsIgnoreCase("")) {

        } else {
            String[] data = s.split("@");
            if (LanguageApp.getLanguage(context) == LanguageApp.LANGUAGE_VI) {
                for (int i = 0; i < data.length; i++) {
                    String[] line = data[i].split(",");
                    if (line[3].equalsIgnoreCase("1")) {
                        if (line[2].equalsIgnoreCase("3")) {//con
                            arrayListFavorite.add(data[i - 1].split(",")[0]);
                            arrayListTypeFavorite.add(2);
                        }
                        arrayListFavorite.add(data[i].split(",")[0]);
                        arrayListTypeFavorite.add(3);
                    }
                }
            } else
                for (int i = 0; i < data.length; i++) {
                    String[] line = data[i].split(",");
                    if (line[3].equalsIgnoreCase("1")) {
                        if (line[2].equalsIgnoreCase("3")) {//con
                            arrayListFavorite.add(data[i - 1].split(",")[1]);
                            arrayListTypeFavorite.add(2);
                        }
                        arrayListFavorite.add(data[i].split(",")[1]);
                        arrayListTypeFavorite.add(3);
                    }
                }
        }
        return arrayListFavorite;
    }

    public static ArrayList<String> getArrayListTitle() {
        return arrayListTitle;
    }

    private static ArrayList<String> getArrayListTitlePrivateEN() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = new ArrayList<>();
        //1
        arrayList.add("Favorites");
        //1
        arrayList.add("Thị trường");
        //2
        arrayList.add("Overview");
        arrayList.add("Watchlist");
        arrayList.add("Derived Watchlist");
        arrayList.add("News");
        arrayList.add("Events");
        arrayList.add("Chart");
        arrayList.add("World Indexes");
        //1
        arrayList.add("Giao dịch");
        //2
        arrayList.add("Place Orders");
        //2
        arrayList.add("Transaction Report");
        //3
        arrayList.add("Tra cứu Số dư");
        arrayList.add("Lệnh chờ khớp");
        arrayList.add("KQ khớp lệnh");
        arrayList.add("Lệnh trong ngày");
        arrayList.add("Chờ thanh toán");
        //2
        arrayList.add("Money Transfer");
        //3
        arrayList.add("Lập lệnh");
        arrayList.add("Mẫu chuyển tiền");
        arrayList.add("Lịch sử chuyển tiền");
        //2
        arrayList.add("Bán lô lẻ");
        //3
        arrayList.add("Lập lệnh");
        arrayList.add("Lịch sử bán");
        //2
        arrayList.add("Thực hiện quyền");
        arrayList.add("Stoploss");
        //3
        arrayList.add("Lập lệnh");
        arrayList.add("Lịch sử đặt tiền");
        //2
        arrayList.add("Margin");
        //3
        arrayList.add("Danh sách hợp đồng");
        arrayList.add("Cầm cố");
        arrayList.add("Trả tiền hợp đồng");
        arrayList.add("Gia hạn");
        arrayList.add("Thay đổi hạn mức");
        //2
        arrayList.add("Lưu ký chứng khoán");
        arrayList.add("Ứng trước");
        //3
        arrayList.add("Ứng tiền cố tức");
        arrayList.add("Lịch sử ứng trước");
        //2
        arrayList.add("Báo cáo tài sản");
        arrayList.add("Tra cứu phí lưu ký");
        //1
        arrayList.add("Tiện ích");
        //2
        arrayList.add("Thông tin tài chính khác");
        arrayList.add("Thông báo từ FPTS");
//        //2
//        arrayList.add("FPTS Nhận định");
//        //3
//        arrayList.add("Thị trường");
//        arrayList.add("Doanh nghiệp");
//        arrayList.add("Ngành");
//        arrayList.add("Bản tin FPTS");
//        //2
//        arrayList.add("Thị trường tài chính");
//        //3
//        arrayList.add("Tỉ giá");
//        arrayList.add("Lãi suất");
//        arrayList.add("Giá vàng");
//        arrayList.add("Giá dầu");
//        //2
//        arrayList.add("FPTS's Notify");
        //1
        arrayList.add("Trợ giúp");
        //2
        arrayList.add("Create Account");
        arrayList.add("Contack");
        arrayList.add("Feedback");
        arrayList.add("Guide");

        //1
        arrayList.add("Settings");
        return arrayList;
    }

    private static ArrayList<String> getArrayListTitlePrivate() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = new ArrayList<>();
        //1
        arrayList.add("Yêu thích");
        //1
        arrayList.add("Thị trường");
        //2
        arrayList.add("Tổng quan");
        arrayList.add("Bảng giá");
        arrayList.add("Bảng giá Phái sinh");
        arrayList.add("Tin tức");
        arrayList.add("Lịch sự kiện");
        arrayList.add("Biểu đồ");
        arrayList.add("Chỉ số thế giới");
        //1
        arrayList.add("Giao dịch");
        //2
        arrayList.add("Đặt lệnh");
        //2
        arrayList.add("Báo cáo giao dịch");
        //3
        arrayList.add("Tra cứu Số dư");
        arrayList.add("Lệnh chờ khớp");
        arrayList.add("KQ khớp lệnh");
        arrayList.add("Lệnh trong ngày");
        arrayList.add("Chờ thanh toán");
        //2
        arrayList.add("Chuyển tiền");
        //3
        arrayList.add("Lập lệnh");
        arrayList.add("Mẫu chuyển tiền");
        arrayList.add("Lịch sử chuyển tiền");
        //2
        arrayList.add("Bán lô lẻ");
        //3
        arrayList.add("Lập lệnh");
        arrayList.add("Lịch sử bán");
        //2
        arrayList.add("Thực hiện quyền");
        arrayList.add("Stoploss");
        //3
        arrayList.add("Lập lệnh");
        arrayList.add("Lịch sử đặt tiền");
        //2
        arrayList.add("Ký quỹ");
        //3
        arrayList.add("Danh sách hợp đồng");
        arrayList.add("Cầm cố");
        arrayList.add("Trả tiền hợp đồng");
        arrayList.add("Gia hạn");
        arrayList.add("Thay đổi hạn mức");
        //2
        arrayList.add("Lưu ký chứng khoán");
        arrayList.add("Ứng trước");
        //3
        arrayList.add("Ứng tiền cố tức");
        arrayList.add("Lịch sử ứng trước");
        //2
        arrayList.add("Báo cáo tài sản");
        arrayList.add("Tra cứu phí lưu ký");
        //1
        arrayList.add("Tiện ích");
        //2
        arrayList.add("Thông tin tài chính khác");
        arrayList.add("Thông báo từ FPTS");
//        //2
//        arrayList.add("FPTS Nhận định");
//        //3
//        arrayList.add("Thị trường");
//        arrayList.add("Doanh nghiệp");
//        arrayList.add("Ngành");
//        arrayList.add("Bản tin FPTS");
//        //2
//        arrayList.add("Thị trường tài chính");
//        //3
//        arrayList.add("Tỉ giá");
//        arrayList.add("Lãi suất");
//        arrayList.add("Giá vàng");
//        arrayList.add("Giá dầu");
//        //2
//        arrayList.add("Thông báo từ FPTS");
        //1
        arrayList.add("Trợ giúp");
        //2
        arrayList.add("Mở tài khoản");
        arrayList.add("Liên hệ");
        arrayList.add("Góp ý");
        arrayList.add("Hướng dẫn sử dụng");

        //1
        arrayList.add("Thiết lập");
        return arrayList;
    }

    public static ArrayList<Integer> getArrayListType(Context context) {
        if (arrayListType == null || arrayListType.size() == 0)
            arrayListType = getArrayListTypePrivate();
        return arrayListType;
    }

    private static ArrayList<Integer> getArrayListTypePrivate() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer[] array = {1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 3, 3, 3, 3, 3, 2, 3, 3, 3, 2, 3, 3, 2, 2, 3, 3, 2, 3
                , 3, 3, 3, 3, 2, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1};

        for (int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }
        return arrayList;
    }

    public static boolean isFavorite(Context context, int pos) {
        return isFavoritePrivate(context, pos);
    }

    private static boolean isFavoritePrivate(Context context, int pos) {
        String saved = FileInputAndOutputStream.readData(context, FileMenuFavorite);
        //childCount == 0
        String[] data = saved.split("@");
        for (int i = 0; i < data.length; i++) {
            if (data[i].contains(arrayListTitle.get(pos))) {
                if (data[i].split(",")[3].equalsIgnoreCase("1"))
                    return true;
                else break;
            }
        }
        return false;
    }

    public static void saveFavorite(Context context, ArrayList<String> arrayList) {
        saveFavoritePrivate(context, arrayList);
    }

    private static void saveFavoritePrivate(Context context, ArrayList<String> arrayList) {
        arrayListSaved = arrayList;

        String str = FileInputAndOutputStream.readData(context, FileMenuFavorite).replace("\n", "");
        String[] data = str.split("@");
        for (int i = 0; i < data.length; i++) {
            String vi = data[i].split(",")[0];
            String en = data[i].split(",")[1];
            String type = data[i].split(",")[2];
            String save = arrayList.get(i);
            data[i] = vi + "," + en + "," + type + "," + save;
        }
        str = "";
        for (int i = 0; i < data.length - 1; i++) {
            str += data[i] + "@";
        }
        str += data[data.length - 1];
        FileInputAndOutputStream.saveData(context, "", FileMenuFavorite);
        FileInputAndOutputStream.saveData(context, str, FileMenuFavorite);
    }

    public static void deleteFavorite(Context context, int pos) {
        deleteFavoritePrivate(context, pos);
    }

    private static void deleteFavoritePrivate(Context context, int pos) {
        String str = FileInputAndOutputStream.readData(context, FileMenuFavorite);
        String[] data = str.split("@");
        for (int i = 0; i < data.length; i++)
            if (data[i].contains(arrayListTitle.get(pos))) {
                String vi = data[i].split(",")[0];
                String en = data[i].split(",")[1];
                String type = data[i].split(",")[2];
                String save = "0";
                data[i] = vi + "," + en + "," + type + "," + save;

                break;
            }

        str = "";
        for (int i = 0; i < data.length - 1; i++) {
            str += data[i] + "@";
        }
        str += data[data.length - 1];
        FileInputAndOutputStream.saveData(context, "", FileMenuFavorite);
        FileInputAndOutputStream.saveData(context, str, FileMenuFavorite);
    }

}
