# 実行方法
1. DockerでDBサーバーを構築する。`docker compose up -d`をコマンドラインで実行して、コンテナを作成する
2. server/src/main/kotlin/org/example/project/Application.ktにある`main`関数を実行する


# サーバーサイド
使用技術
- 言語:Kotlin "2.0.20"
- フレームワーク:ktor "3.0.0"
- データベース:postgres
- リアルタイム通信:WebSocket
- DI:Koin (予定)
## 概要
FitBattleで使用するAPIサーバーFitBattleで使用するAPIサーバー
## APIのエンドポイント

# JPHACK
Base URLs:http://0.0.0.0:7070
# Authentication

# Default

## POST ログイン

POST /api/v1/user/login

ログインする際に必要なAPI

> Body Parameters

```json
{
  "email": "太一.松本48@gmail.com",
  "password": "labore do"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|body|body|object| no |none|
|» email|body|string| yes |メールアドレス|
|» password|body|string| yes |パスワード|

> Response Examples

```json
{
  "name": "はじめて たくす 輸出",
  "token": "sint",
  "userId": 24259591
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» name|string|true|none||ユーザー名|
|» token|string|true|none||認証情報|
|» userId|integer|true|none||ユーザーID|

## POST アカウント作成

POST /api/v1/user/create

アカウント作成を行うためのAPI

> Body Parameters

```json
{
  "name": "かんしん 明治 めいし",
  "email": "太一.松本48@gmail.com",
  "password": "labore do"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|body|body|object| no |none|
|» name|body|string| yes |名前|
|» email|body|string| yes |メール|
|» password|body|string| yes |パスワード|

> Response Examples

```json
{
  "userId": 1,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6Imh0dHA6Ly8wLjAuMC4wOjcwNzAvIiwiYXVkIjoiaHR0cDovLzAuMC4wLjA6NzA3MC9hcGkvdjEvIiwidXNlckVtYWlsIjoiRnVqaXN1ZUtvdWtpQGdtYWlsLmNvbSIsImV4cCI6MTczMDU0Nzg1Nn0.hJFAuSGKMuNqE-q4ujFljE_aY0X7W7QXxdv5BDmvtpo"
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» userId|integer|true|none||ユーザーID|
|» token|string|true|none||認証情報|

## POST ジオフェンスの入場

POST /api/v1/location/geofence/entry

ジオフェンス入室を登録するAPI

> Body Parameters

```json
{
  "userId": 1,
  "geoFenceId": 1,
  "entryTime": "2024-10-25T04:58:10.391Z"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string| no |認証情報|
|body|body|object| no |none|
|» userId|body|integer| yes |ユーザーの識別ID|
|» geoFenceId|body|integer| yes |ジオフェンスの識別ID|
|» entryTime|body|string| yes |入場時刻|

> Response Examples

```json
{
  "success": true,
  "geoFenceEntryLogId": 1,
  "token": "token",
  "passingMember": [
    {
      "id": 1,
      "name": "name",
      "iconUrl": "iconUrl"
    }
  ]
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» success|boolean|true|none||成功を判定|
|» geoFenceEntryLogId|integer|true|none||入出管理ID|
|» passingMember|[object]|true|none||すれ違ったユーザー|
|»» id|integer|false|none||ユーザーID|
|»» name|string|false|none||ユーザー名|
|»» iconUrl|string|false|none||ユーザーアイコンURL|

## POST ジオフェンスの退場

POST /api/v1/location/geofence/exit

> Body Parameters

```json
{
  "geoFenceEntryLogId": 1,
  "userId": 1,
  "geoFenceId": 1,
  "exitTime": "2024-10-25T04:58:10.391Z"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string| no |none|
|body|body|object| no |none|
|» geoFenceEntryLogId|body|integer| yes |入出管理ID|
|» userId|body|integer| yes |ユーザーID|
|» geoFenceId|body|integer| yes |ジオフェンスの識別ID|
|» exitTime|body|string| yes |退場時間|

> Response Examples

> 200 Response

```json
{
  "success": true
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» success|boolean|true|none||成功判定|

## GET フィットネス情報を取得

GET /api/v1/fitness

フィットネス情報を取得する

> Body Parameters

```json
{
  "userId": 62795097
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string| no |認証情報|
|body|body|object| no |none|
|» userId|body|integer| yes |ユーザID|

> Response Examples

```json
{
  "success": true,
  "fitness": {
    "userId": 1,
    "calories": 120.3,
    "timestamp": "2024-11-02T01:57:02.482"
  }
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» success|boolean|true|none||成功を判定|
|» fitness|object|true|none||フィットネス情報|
|»» userId|integer|true|none||ユーザーID|
|»» calories|number|true|none||カロリー情報|
|»» timestamp|string|true|none||保存時間|

## POST フィットネス情報を保存

POST /api/v1/fitness/save

フィットネス情報を保存する

> Body Parameters

```json
{
  "userId": 0,
  "calories": 0,
  "timestamp": "string"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string| no |認証情報|
|body|body|object| no |none|
|» userId|body|integer| yes |ユーザーID|
|» calories|body|number(float)| yes |カロリー|
|» timestamp|body|string| yes |保存した時間|

> Response Examples

```json
{
  "success": true
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» success|boolean|true|none||成功判定|

## GET すれ違った人を取得する

GET /api/v1/passed

今日すれ違った人を取得するAPI

> Body Parameters

```json
{
  "userId": 0,
  "timestamp": "string"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string| no |認証情報|
|body|body|object| no |none|
|» userId|body|integer| yes |ユーザーID|
|» timestamp|body|string| yes |現在時刻|

> Response Examples

```json
{
  "success": true,
  "passedUser": [
    {
      "id": 1,
      "name": "test1",
      "iconUrl": "https://someurl.com"
    },
    {
      "id": 2,
      "name": "test2",
      "iconUrl": "https://someurl.com"
    },
    {
      "id": 3,
      "name": "test3",
      "iconUrl": "https://someurl.com"
    },
    {
      "id": 5,
      "name": "test5",
      "iconUrl": "https://someurl.com"
    },
    {
      "id": 8,
      "name": "test8",
      "iconUrl": "https://someurl.com"
    },
    {
      "id": 11,
      "name": "test11",
      "iconUrl": "https://someurl.com"
    },
    {
      "id": 12,
      "name": "test12",
      "iconUrl": "https://someurl.com"
    }
  ]
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

HTTP Status Code **200**

|Name|Type|Required|Restrictions|Title|description|
|---|---|---|---|---|---|
|» success|boolean|true|none||成功判別|
|» passedUser|[object]|true|none||すれ違ったユーザーリスト|
|»» id|integer|true|none||ユーザーID|
|»» name|string|true|none||ユーザー名|
|»» iconUrl|string|true|none||ユーザーアイコン|

# Data Schema




## データベースの設計
