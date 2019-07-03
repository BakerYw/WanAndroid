package com.nyw.domain.common.util.cache;

import com.blankj.utilcode.util.SPUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BakerJ
 * @date 2018/4/3
 */
public class SearchCacheUtil {
    private static final String SPNAME_SEARCH = "SPNAME_SEARCH";
    private static final String SPKEY_SEARCH_HISTORY = "SPKEY_SEARCH_HISTORY";

    public static boolean addSearchHistory(String keyword) {
        keyword = keyword.trim();
        List<String> set = new ArrayList<>(getSearchHistory());
        if (!set.contains(keyword)) {
            set.add(0, keyword);
            SPUtils.getInstance(SPNAME_SEARCH).put(SPKEY_SEARCH_HISTORY, getHistoryStrFromList
                    (set));
            return true;
        }
        return false;
    }

    public static List<String> getSearchHistory() {
        return Arrays.asList(SPUtils.getInstance(SPNAME_SEARCH).getString(SPKEY_SEARCH_HISTORY,
                "").split(" , "));
    }

    private static String getHistoryStrFromList(List<String> str) {
        StringBuilder stringBuilder = new StringBuilder("");
        int size = str.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append(" , ");
            }
            stringBuilder.append(str.get(i));
        }
        return stringBuilder.toString();
    }

    public static void clearHistorySearch() {
        SPUtils.getInstance(SPNAME_SEARCH).put(SPKEY_SEARCH_HISTORY, "");
    }
}
