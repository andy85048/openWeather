# OpenWeatherMap API Key 設置指南 🔑

## 為什麼需要API Key？
Weather Test應用使用OpenWeatherMap的免費API來獲取天氣數據。您需要一個有效的API Key才能正常使用應用。

## 🆓 免費獲取API Key (推薦)

### 步驟1: 註冊帳號
1. 前往 [OpenWeatherMap](https://openweathermap.org/api)
2. 點擊 "Sign Up" 註冊免費帳號
3. 填寫基本資訊並驗證郵箱

### 步驟2: 獲取API Key
1. 登入後進入 [API Keys 頁面](https://home.openweathermap.org/api_keys)
2. 複製您的預設API Key
3. 或創建新的API Key

### 步驟3: 設置API Key
1. 打開 `core/network/build.gradle.kts` 文件
2. 找到第15行：
   ```kotlin
   buildConfigField("String", "WEATHER_API_KEY", "\"demo_api_key_replace_with_real_key\"")
   ```
3. 替換為您的真實API Key：
   ```kotlin
   buildConfigField("String", "WEATHER_API_KEY", "\"YOUR_ACTUAL_API_KEY_HERE\"")
   ```

### 步驟4: 重新編譯安裝
```bash
./gradlew clean installDebug
```

## 📱 測試應用功能

安裝完成後，應用將能夠：
- ✅ 顯示當前天氣
- ✅ 顯示7天天氣預報
- ✅ 切換不同城市
- ✅ 離線緩存數據

## ⚠️ 重要提醒

- **免費額度**: 每月60,000次API調用
- **激活時間**: 新API Key可能需要10-15分鐘激活
- **安全性**: 不要將API Key上傳到公共代碼庫

## 🔧 常見問題

### Q: 顯示"API Key無效"錯誤？
A:
1. 確認API Key正確複製
2. 等待15分鐘讓新Key激活
3. 檢查OpenWeatherMap帳號狀態

### Q: 應用無法獲取天氣數據？
A:
1. 檢查網路連接
2. 確認API Key有效
3. 查看是否超出使用額度

### Q: 如何查看API使用統計？
A: 登入OpenWeatherMap帳號，進入Statistics頁面查看

## 🎯 預設城市

應用內建以下測試城市：
- 台北 (25.0330°N, 121.5654°E)
- 東京 (35.6762°N, 139.6503°E)
- 紐約 (40.7128°N, -74.0060°E)
- 倫敦 (51.5074°N, -0.1278°E)
- 雪梨 (-33.8688°S, 151.2093°E)
- 巴黎 (48.8566°N, 2.3522°E)

## 🎉 完成！

設置完成後，您就可以享受完整的天氣預報功能了！