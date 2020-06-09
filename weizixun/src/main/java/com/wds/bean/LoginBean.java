package com.wds.bean;


public class LoginBean {

    /**
     * code : 200
     * message : 认证成功
     * user : {"userid":"anfly","password":"123456","name":"783xpD","headerpic":"http://47.110.151.50/p6image/img/per.jpg"}
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImV4cCI6MTU5MTc3MjMyOSwidXNlcm5hbWUiOiJnb3VkYW4ifQ.3q6L2N1Oc1vp31VinKeNe-VG__tLd48TnuuejViJ3xE
     */

    private String code;
    private String message;
    private UserBean user;
    private String token;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserBean {
        /**
         * userid : anfly
         * password : 123456
         * name : 783xpD
         * headerpic : http://47.110.151.50/p6image/img/per.jpg
         */

        private String userid;
        private String password;
        private String name;
        private String headerpic;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeaderpic() {
            return headerpic;
        }

        public void setHeaderpic(String headerpic) {
            this.headerpic = headerpic;
        }
    }
    /*
    *//**
     * data : {"admin":false,"chapterTops":[],"collectIds":[12077,12078,12079,12091],"email":"","icon":"","id":44699,"nickname":"h1907a","password":"","publicName":"h1907a","token":"","type":0,"username":"h1907a"}
     * errorCode : 0
     * errorMsg :
     *//*

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        *//**
         * admin : false
         * chapterTops : []
         * collectIds : [12077,12078,12079,12091]
         * email :
         * icon :
         * id : 44699
         * nickname : h1907a
         * password :
         * publicName : h1907a
         * token :
         * type : 0
         * username : h1907a
         *//*

        private boolean admin;
        private String email;
        private String icon;
        private int id;
        private String nickname;
        private String password;
        private String publicName;
        private String token;
        private int type;
        private String username;
        private List<?> chapterTops;
        private List<Integer> collectIds;

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPublicName() {
            return publicName;
        }

        public void setPublicName(String publicName) {
            this.publicName = publicName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getChapterTops() {
            return chapterTops;
        }

        public void setChapterTops(List<?> chapterTops) {
            this.chapterTops = chapterTops;
        }

        public List<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<Integer> collectIds) {
            this.collectIds = collectIds;
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }*/
}
