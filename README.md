# ブランチルール
・デフォルトブランチはdevelop-main、各develop/**ブランチからdevelop-mainに向けてPRを立てること

・ブランチを新規作成するときはdevelop-mainからdevelop/**ブランチを切ること。（develop-main ---> develop/android ）

・develop/**から画面単位でfeature/**ブランチを切ること。（develop/android --> feature/todo_screen）

・feature/**から先のブランチは機能単位で同様にfeature/**で自由にブランチを切ってOK

## イメージ

<img width="386" alt="スクリーンショット 2024-10-06 18 57 03" src="https://github.com/user-attachments/assets/6bee673d-06ec-461f-ab35-a5b185770e88">

# push前にすること
## kotlin 

ターミナルで `ktlint --format` を実行（Kotlinのフォーマットに沿ってコードを綺麗にしてくれる）
インストールは下記参照
https://github.com/pinterest/ktlint?tab=readme-ov-file


## swift

# Github Actionsが動くケース
・feature/**に向かってpushしたとき

・develop-mainに向かってPRを立てたとき

基本的にはブランチルールを守っていれば動くはず








# This is a Kotlin Multiplatform project targeting Android, iOS, Server.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
