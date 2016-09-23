package com.myhome.entity;


import java.util.Map;
import java.util.HashMap;


public enum EntityTypeEnum {

    Page(Page.class, 1),
    Region(Region.class, 2),
    Tag(Tag.class, 3),
    User(User.class, 4),
    UserInfo(UserInfo.class, 5),
    Role(Role.class, 6),
    Permission(Permission.class, 7),
    RolePermissionItem(RolePermissionItem.class, 8),
    UserRoleItem(UserRoleItem.class, 9),
    UserPermissionItem(UserPermissionItem.class, 10),
    Authentication(Authentication.class, 11),
    LoginAuthentication(LoginAuthentication.class, 12),
    EmailAuthentication(EmailAuthentication.class, 13),
    MobileAuthentication(MobileAuthentication.class, 14),
    QQAuthentication(QQAuthentication.class, 15),
    WeiboAuthentication(WeiboAuthentication.class, 16),
    WeixinAuthentication(WeixinAuthentication.class, 17),
    Artist(Artist.class, 18),
    ArtistInfo(ArtistInfo.class, 19),
    Works(Works.class, 20),
    Voting(Voting.class, 21),
    Praise(Praise.class, 22),
    Game(Game.class, 23),
    GameWorksItem(GameWorksItem.class, 24),
    WorksPictureItem(WorksPictureItem.class, 25),
    WorksTagItem(WorksTagItem.class, 26),
    Comment(Comment.class, 27),
    UserArtistItem(UserArtistItem.class, 28);

    private static Map<Class<?>, EntityTypeEnum> entityClassCache = new HashMap<Class<?>, EntityTypeEnum>();
    private static Map<Integer, EntityTypeEnum> codeCache = new HashMap<Integer, EntityTypeEnum>();

    static {
        for (EntityTypeEnum entityTypeEnum : EntityTypeEnum.values()) {
            entityClassCache.put(entityTypeEnum.entityClass, entityTypeEnum);
            codeCache.put(entityTypeEnum.code, entityTypeEnum);
        }
    }

    private Class<?> entityClass;
    private int code;

    private EntityTypeEnum(Class entityClass, int code) {
        this.entityClass = entityClass;
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public Class<?> getEntityClass() {
        return this.entityClass;
    }

    public static EntityTypeEnum valueOf(Class<?> entityClass) {
        return entityClassCache.get(entityClass);
    }

    public static EntityTypeEnum valueOf(Integer code) {
        return codeCache.get(code);
    }

}
