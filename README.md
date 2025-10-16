# 深圳住房规划 Android 应用

这是一个专为深圳市民设计的住房规划应用，提供住房政策查询、项目信息浏览、申请指南和地图查看等功能。

## 功能特性

### 🏠 主要功能
- **政策查询**: 查看最新的住房政策信息
- **住房项目**: 浏览各类住房项目详情
- **申请指南**: 了解住房申请流程和条件
- **地图查看**: 在地图上查看项目位置

### 📱 技术特性
- 使用 Jetpack Compose 构建现代化 UI
- 采用 MVVM 架构模式
- 使用 Room 数据库进行本地数据存储
- 集成 Hilt 依赖注入框架
- 支持深色主题

## 技术栈

- **UI**: Jetpack Compose, Material Design 3
- **架构**: MVVM, Repository Pattern
- **数据库**: Room
- **依赖注入**: Hilt
- **网络**: Retrofit, OkHttp
- **图片加载**: Coil
- **地图**: Google Maps API

## 项目结构

```
app/
├── src/main/java/com/shenzhen/housing/
│   ├── data/
│   │   ├── database/          # 数据库相关
│   │   ├── model/             # 数据模型
│   │   └── repository/        # 数据仓库
│   ├── di/                    # 依赖注入
│   ├── ui/
│   │   ├── components/        # UI 组件
│   │   ├── navigation/        # 导航
│   │   ├── screens/           # 屏幕
│   │   ├── theme/             # 主题
│   │   └── viewmodel/         # ViewModel
│   ├── MainActivity.kt
│   └── HousingApplication.kt
└── src/main/res/              # 资源文件
```

## 安装和运行

### 环境要求
- Android Studio Arctic Fox 或更高版本
- JDK 8 或更高版本
- Android SDK API 24 或更高版本

### 构建步骤
1. 克隆项目到本地
2. 使用 Android Studio 打开项目
3. 等待 Gradle 同步完成
4. 连接 Android 设备或启动模拟器
5. 点击运行按钮

### 配置
- 如需使用地图功能，请在 `google-services.json` 中配置 Google Maps API 密钥
- 确保在 `AndroidManifest.xml` 中正确配置权限

## 主要屏幕

### 首页
- 应用概览和功能介绍
- 最新住房资讯展示
- 快速导航到各功能模块

### 政策查询
- 按分类浏览住房政策
- 搜索特定政策内容
- 查看政策详细信息和发布时间

### 住房项目
- 浏览各类住房项目
- 按类型、区域筛选项目
- 查看项目详细信息（价格、面积、状态等）

### 申请指南
- 了解申请条件和流程
- 查看所需材料清单
- 掌握申请时间安排

### 地图查看
- 在地图上查看项目位置
- 查看附近项目信息
- 获取交通和位置信息

## 开发计划

- [ ] 添加用户登录和个人中心
- [ ] 集成实时数据更新
- [ ] 添加推送通知功能
- [ ] 优化地图交互体验
- [ ] 添加离线数据支持

## 贡献

欢迎提交 Issue 和 Pull Request 来改进这个项目。

## 许可证

本项目采用 MIT 许可证。
