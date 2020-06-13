package com.wds.bean;

import java.util.List;

public class DailyListBean {
    /**
     * date : 20200425
     * stories : [{"image_hue":"0x687854","title":"小事 · 博士生学历真的很重要吗？","url":"https://daily.zhihu.com/story/9723139","hint":"VOL.1190","ga_prefix":"042507","images":["https://pic1.zhimg.com/v2-c1a5203af2d379907b221ac6898f326c.jpg"],"type":0,"id":9723139},{"image_hue":"0xb39e74","title":"原来这么多年我看的地图都是不准的？","url":"https://daily.zhihu.com/story/9723034","hint":"李狗嗨 · 1 分钟阅读","ga_prefix":"042507","images":["https://pic3.zhimg.com/v2-599cc6edf7e69f4106085cff003012ee.jpg"],"multipic":true,"type":0,"id":9723034},{"image_hue":"0xb39f7d","title":"人类有哪些天敌？","url":"https://daily.zhihu.com/story/9723105","hint":"韩白鲤 · 2 分钟阅读","ga_prefix":"042507","images":["https://pic3.zhimg.com/v2-22a50bdc0cca33d944d34e93c08cbd66.jpg"],"multipic":true,"type":0,"id":9723105},{"image_hue":"0x141414","title":"未来的中国航天，有这些值得期待","url":"https://daily.zhihu.com/story/9723123","hint":"鸑鷟鹓鶵 · 6 分钟阅读","ga_prefix":"042507","images":["https://pic1.zhimg.com/v2-a272692cb223dc321edb131c52e77854.jpg"],"multipic":true,"type":0,"id":9723123},{"image_hue":"0xab8a78","title":"为什么孩子会反复看同一集动画片？","url":"https://daily.zhihu.com/story/9723125","hint":"米宝的爸 · 3 分钟阅读","ga_prefix":"042507","images":["https://pic1.zhimg.com/v2-12c6c0fd5a5cec0f7d91e7baa40e49b0.jpg"],"type":0,"id":9723125},{"image_hue":"0x3f477c","title":"为什么动漫里出招式总是要很中二地喊出来？","url":"https://daily.zhihu.com/story/9723115","hint":"许多的小兵器 · 1 分钟阅读","ga_prefix":"042507","images":["https://pic4.zhimg.com/v2-ef4ca5b327395dd2b3a0f705a778ec6f.jpg"],"type":0,"id":9723115}]
     * top_stories : [{"image_hue":"0x687854","hint":"作者 / 时间规划局","url":"https://daily.zhihu.com/story/9723139","image":"https://pic4.zhimg.com/v2-976e6c061aed1a39998992f3dbc8b71b.jpg","title":"小事 · 博士生学历真的很重要吗？","ga_prefix":"042507","type":0,"id":9723139},{"image_hue":"0x6e5a49","hint":"作者 / 芝麻酱","url":"https://daily.zhihu.com/story/9723068","image":"https://pic2.zhimg.com/v2-076656312e4d6c67a46e026931d13bbd.jpg","title":"喝牛奶的人和不喝牛奶的人差别在哪？","ga_prefix":"042407","type":0,"id":9723068},{"image_hue":"0x454030","hint":"作者 / 星球研究所","url":"https://daily.zhihu.com/story/9723052","image":"https://pic4.zhimg.com/v2-9760bb365083e93202f825dcd9d40b37.jpg","title":"为什么我们要保护地球？","ga_prefix":"042307","type":0,"id":9723052},{"image_hue":"0xb39059","hint":"作者 / Mr.X","url":"https://daily.zhihu.com/story/9722986","image":"https://pic2.zhimg.com/v2-57e94f61938e15bd9d44e4365b0cdabd.jpg","title":"原油价格为什么会跌到变负值？","ga_prefix":"042207","type":0,"id":9722986},{"image_hue":"0xb3907d","hint":"作者 / 吴升知","url":"https://daily.zhihu.com/story/9722967","image":"https://pic1.zhimg.com/v2-72bd97d568340d9767a5131f8fb29610.jpg","title":"下一个革命性的人机交互方式会是什么？","ga_prefix":"042107","type":0,"id":9722967}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * image_hue : 0x687854
         * title : 小事 · 博士生学历真的很重要吗？
         * url : https://daily.zhihu.com/story/9723139
         * hint : VOL.1190
         * ga_prefix : 042507
         * images : ["https://pic1.zhimg.com/v2-c1a5203af2d379907b221ac6898f326c.jpg"]
         * type : 0
         * id : 9723139
         * multipic : true
         */

        private String image_hue;
        private String title;
        private String url;
        private String hint;
        private String ga_prefix;
        private int type;
        private int id;
        private boolean multipic;
        private List<String> images;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image_hue : 0x687854
         * hint : 作者 / 时间规划局
         * url : https://daily.zhihu.com/story/9723139
         * image : https://pic4.zhimg.com/v2-976e6c061aed1a39998992f3dbc8b71b.jpg
         * title : 小事 · 博士生学历真的很重要吗？
         * ga_prefix : 042507
         * type : 0
         * id : 9723139
         */

        private String image_hue;
        private String hint;
        private String url;
        private String image;
        private String title;
        private String ga_prefix;
        private int type;
        private int id;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
