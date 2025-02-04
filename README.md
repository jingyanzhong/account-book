vue
┣ 📂 dist                  # 打包後的檔案
┣ 📂 node_modules          # 套件安裝的目錄
┣ 📂 public                # 靜態資源
┣ 📂 src                   # 源碼目錄
┃ ┣ 📂 assets             # 靜態資源（圖片、CSS、SCSS）
┃ ┃ ┣ 📂 css              # CSS 檔案
┃ ┃ ┃ ┣ 📄 main.css
┃ ┃ ┃ ┗ 📄 main.css.map
┃ ┃ ┣ 📂 img              # 圖片資源
┃ ┃ ┃ ┣ 🖼️ home-hero.jpg
┃ ┃ ┃ ┣ 🖼️ home-title.png
┃ ┃ ┃ ┗ 🖼️ tree-icon-green.png
┃ ┃ ┣ 📂 scss             # SCSS 樣式檔案
┃ ┃ ┃ ┣ 🎨 main.scss
┃ ┃ ┃ ┣ 🎨 _base.scss
┃ ┃ ┃ ┣ 🎨 _home.scss
┃ ┃ ┃ ┣ 🎨 _journal.scss
┃ ┃ ┃ ┣ 🎨 _layout.scss
┃ ┃ ┃ ┣ 🎨 _mixin.scss
┃ ┃ ┃ ┣ 🎨 _model.scss
┃ ┃ ┃ ┣ 🎨 _report.scss
┃ ┃ ┃ ┣ 🎨 _reset.scss
┃ ┃ ┃ ┣ 🎨 _subject.scss
┃ ┃ ┃ ┗ 🎨 _variables.scss
┃ ┃ ┗ 📄 logo.svg
┃ ┣ 📂 components         # 可重用的 Vue 元件
┃ ┃ ┣ 🧩 DeleteModel.vue
┃ ┃ ┣ 🧩 modelComponent.vue
┃ ┃ ┗ 🧩 paginationComponent.vue
┃ ┣ 📂 router             # 路由管理
┃ ┃ ┗ 📄 index.js
┃ ┣ 📂 stores             # 狀態管理（Pinia / Vuex）
┃ ┃ ┣ 🔧 apiServe.js
┃ ┃ ┣ 🔧 isActive.js
┃ ┃ ┣ 🔧 journal.js
┃ ┃ ┣ 🔧 menuShow.js
┃ ┃ ┣ 🔧 modelShow.js
┃ ┃ ┣ 🔧 pagination.js
┃ ┃ ┗ 🔧 thousandthsFormat.js
┃ ┣ 📂 views              # 頁面組件
┃ ┃ ┣ 🏠 AccountView.vue
┃ ┃ ┣ 🏠 HomeView.vue
┃ ┃ ┣ 📖 JournalView.vue
┃ ┃ ┣ 👥 MemberView.vue
┃ ┃ ┣ 🔍 ProfileView.vue
┃ ┃ ┣ 📂 ProjectView.vue
┃ ┃ ┣ 📊 ReportView.vue
┃ ┃ ┗ 📚 SubjectsView.vue
┃ ┣ 📄 App.vue             # Vue 主應用
┃ ┗ 📄 main.js             # Vue 入口文件
┣ ⚙️ .editorconfig
┣ ⚙️ .eslintrc.cjs
┣ ⚙️ .gitignore
┣ ⚙️ .prettierrc.json
┣ ⚙️ eslint.config.js
┣ 📝 index.html           # 主 HTML 檔案
┣ 🛠️ jsconfig.json
┣ 📦 package-lock.json
┣ 📦 package.json
┣ 📖 README.md            # 專案說明文件
┗ ⚙️ vite.config.js        # Vite 設定檔
