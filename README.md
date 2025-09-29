# Weather Test App 🌤️

基於Clean Architecture的Android天氣預報應用，使用Kotlin、Coroutines和Jetpack Compose開發。

## 功能特色 ✨

- **當日天氣預報** - 顯示詳細的當前天氣資訊
- **一週天氣預報** - 查看未來7天的天氣趨勢
- **城市選擇** - 支援多個預設城市切換
- **離線緩存** - 本地數據緩存，離線也能查看
- **下拉刷新** - 手動更新最新天氣資料

## 技術架構 🏗️

### 核心技術棧
- **語言**: Kotlin
- **非同步處理**: Coroutines + Flow
- **UI框架**: Jetpack Compose
- **架構模式**: Clean Architecture
- **依賴注入**: Hilt/Dagger
- **本地儲存**: Room Database
- **網路層**: Retrofit + OkHttp
- **天氣API**: OpenWeatherMap API

### 模組化架構
```
WeatherApp/
├── app/                     # 主應用模組
├── core/                    # 核心共享模組
│   ├── common/             # 通用工具類
│   ├── network/            # 網路層
│   ├── database/           # 資料庫層
│   └── ui/                 # UI 共享組件
├── feature/                # 功能模組
│   ├── weather/            # 天氣功能模組
│   └── cities/             # 城市管理功能模組
└── buildSrc/               # 構建配置
```

### Clean Architecture 三層分離
- **Data Layer**: 資料來源管理 (API + 本地緩存)
- **Domain Layer**: 業務邏輯核心 (Use Cases + Entities)
- **Presentation Layer**: UI 展示層 (ViewModels + Compose UI)

## 開始使用 🚀

### 環境要求
- Android Studio Hedgehog | 2023.1.1 或更新版本
- Kotlin 1.9.20+
- Android SDK 24+

### 構建專案
```bash
# 克隆專案
git clone [your-repo-url]
cd WeatherTest

# 構建Debug版本
./gradlew assembleDebug

# 運行測試
./gradlew test

# 安裝到設備
./gradlew installDebug
```

### API Key 配置
專案使用OpenWeatherMap API，需要配置API Key：

1. 註冊 [OpenWeatherMap](https://openweathermap.org/api) 帳號
2. 獲取免費API Key
3. 修改 `core/network/build.gradle.kts` 中的API Key：
```kotlin
buildConfigField("String", "WEATHER_API_KEY", "\"YOUR_API_KEY_HERE\"")
```

## 專案特點 🌟

### 響應式設計
- Material 3 設計語言
- 支援深色/淺色主題
- 流暢的動畫過渡

### 性能優化
- 本地數據緩存 (5分鐘過期機制)
- 智能數據獲取策略
- 離線模式支援

### 代碼品質
- 完整的錯誤處理
- 單元測試覆蓋
- 遵循 Android 開發最佳實踐

## 預設城市 🌍
應用內建以下城市：
- 台北 (Taipei) 🇹🇼
- 東京 (Tokyo) 🇯🇵
- 紐約 (New York) 🇺🇸
- 倫敦 (London) 🇬🇧
- 雪梨 (Sydney) 🇦🇺
- 巴黎 (Paris) 🇫🇷# openWeather
