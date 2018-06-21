package com.fx.repository;

import com.fx.model.User;
import com.fx.repository.impl.UserRepositoryImpl;
import com.fx.util.TimeUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 19:37 2018/6/21/021
 */
public class UserRepositoryCreateTest {
    UserRepository userRepository = new UserRepositoryImpl();

    @Test
    public void create1() {
        String[] address = {"海门", "鄂尔多斯", "招远", "舟山", "齐齐哈尔", "盐城", "赤峰", "青岛", "乳山", "金昌", "泉州", "莱西", "日照", "胶南", "南通", "拉萨", "云浮", "梅州", "文登", "上海", "攀枝花", "威海", "承德", "厦门", "汕尾", "潮州", "丹东", "太仓", "曲靖", "烟台", "福州", "瓦房店", "即墨", "抚顺", "玉溪", "张家口", "阳泉", "莱州", "湖州", "汕头", "昆山", "宁波", "湛江", "揭阳", "荣成", "连云港", "葫芦岛", "常熟", "东莞", "河源", "淮安", "泰州", "南宁", "营口", "惠州", "江阴", "蓬莱", "韶关", "嘉峪关", "广州", "延安", "太原", "清远", "中山", "昆明", "寿光", "盘锦", "长治", "深圳", "珠海", "宿迁", "咸阳", "铜川", "平度", "佛山", "海口", "江门", "章丘", "肇庆", "大连", "临汾", "吴江", "石嘴山", "沈阳", "苏州", "茂名", "嘉兴", "长春", "胶州", "银川", "张家港", "三门峡", "锦州", "南昌", "柳州", "三亚", "自贡", "吉林", "阳江", "泸州", "西宁", "宜宾", "呼和浩特", "成都", "大同", "镇江", "桂林", "张家界", "宜兴", "北海", "西安", "金坛", "东营", "牡丹江", "遵义", "绍兴", "扬州", "常州", "潍坊", "重庆", "台州", "南京", "滨州", "贵阳", "无锡", "本溪", "克拉玛依", "渭南", "马鞍山", "宝鸡", "焦作", "句容", "北京", "徐州", "衡水", "包头", "绵阳", "乌鲁木齐", "枣庄", "杭州", "淄博", "鞍山", "溧阳", "库尔勒", "安阳", "开封", "济南", "德阳", "温州", "九江", "邯郸", "临安", "兰州", "沧州", "临沂", "南充", "天津", "富阳", "泰安", "诸暨", "郑州", "哈尔滨", "聊城", "芜湖", "唐山", "平顶山", "邢台", "德州", "济宁", "荆州", "宜昌", "义乌", "丽水", "洛阳", "秦皇岛", "株洲", "石家庄", "莱芜", "常德", "保定", "湘潭", "金华", "岳阳", "长沙", "衢州", "廊坊", "菏泽", "合肥", "武汉", "大庆"};
        String[] hotTags = {"电脑", "花", "植物", "动物", "轮船", "人像", "美女", "自动步枪", "美食", "风景", "图书", "建筑", "自然景观", "学生", "明星", "狗", "猫", "树山", "日落", "食物", "汽车", "飞机", "兔子"};
        String username = "test";
        String password = "123456";
        int labelMin = 100;
        int labelMax = 800;
        int expMax = 2500;
        int levelMax = 5;
        String [] roles = {"Worker","Requestor"};
        TimeUtil t = new TimeUtil();
        for (int i = 0; i < 400; i++) {
            User user = new User();
            int random = (int) (address.length * Math.random());
            user.setCity(address[random]);
            List<String> tags = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                random = (int) (tags.size() * Math.random());
                if (!tags.contains(hotTags[random]))
                    tags.add(hotTags[random]);
            }
            random = (int)(Math.random() * 2);
            user.setUsername(username + i);
            user.setRole(roles[random]);
            user.setLatestSignIn(t.minusDay((int)(Math.random()* 15)).toString());

            String registerTime =  new TimeUtil(user.getLatestSignIn()).minusDay((int)(Math.random() * 7 + 1)).toString();
            user.setRegisterTime(registerTime);
            user.setLevel((int)(levelMax * Math.random()));
            user.setExp((int)(Math.random() * expMax));
            user.setPassword(password);
            user.setLabelNum(labelMin + (int)(Math.random()*(labelMax - labelMin) ));
            userRepository.addUser(user);
        }
    }

}