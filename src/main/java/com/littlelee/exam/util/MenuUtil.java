package com.littlelee.exam.util;

import com.littlelee.exam.entity.admin.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于菜单操作的一些公用方法
 * @author littlelee
 * @date 2020/2/29 9:00
 */
public class MenuUtil {

    /**
     * 从给定的菜单中返回所有顶级菜单
     * @param menuList
     * @return
     */
    public static List<Menu> getAllTopMenu(List<Menu> menuList){
        List<Menu> ret = new ArrayList<>();
        for(Menu menu:menuList){
            if(menu.getParentId() == 0){
                ret.add(menu);
            }
        }
        return ret;
    }

    /**
     * 获取所有的二级菜单
     * @param menuList
     * @return
     */
    public static List<Menu> getAllSecondMenu(List<Menu> menuList){
        List<Menu> ret = new ArrayList<>();
        List<Menu> allTopMenu = getAllTopMenu(menuList);
        for(Menu menu:menuList){
            for(Menu topMenu:allTopMenu){
                if(menu.getParentId() == topMenu.getId()){
                    ret.add(menu);
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * 获取某个二级菜单下的按钮
     * @param menuList
     * @param secondMenuId
     * @return
     */
    public static List<Menu> getAllThirdMenu(List<Menu> menuList,Long secondMenuId){
        List<Menu> ret = new ArrayList<>();
        for(Menu menu:menuList){
            if(menu.getParentId() == secondMenuId){
                ret.add(menu);
            }
        }
        return ret;
    }
}
