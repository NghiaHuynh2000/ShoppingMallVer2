package hcmute.edu.vn.mssv18110326.Common;


import com.quickblox.users.model.QBUser;

import hcmute.edu.vn.mssv18110326.Holder.QBUserHolders;

import java.util.List;

public class Common {
    public static final String DIALOG_EXTRA = "Dialogs";
    public static String createChatDialogName(List<Integer> qbUsers)
    {
        List<QBUser> qbUsers1 = QBUserHolders.getInstance().getUsersByIds(qbUsers);
        StringBuilder name = new StringBuilder();
        for(QBUser user:qbUsers1){
            name.append(user.getFullName()).append("");
        }
        if(name.length() > 30){
            name = name.replace(30, name.length()-1,"...");
        }
        return name.toString();
    }
}
