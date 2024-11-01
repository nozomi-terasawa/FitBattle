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

Base URLs:http://0.0.0.0:7070

# Authentication

# Default

## POST ログイン

POST /api/v1/user/login

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

> Response Examples

> 200 Response

```json
{
  "name": "string",
  "email": "string",
  "token": "string"
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
|» name|string|true|none||none|
|» email|string|true|none||none|
|» token|string|true|none||none|

## POST アカウント作成

POST /api/v1/user/create

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

> 200 Response

```json
{}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

## POST ジオフェンスの入場

POST /api/v1/location/geofence/entry

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
|Authorization|header|string| no |none|
|body|body|object| no |none|
|» userId|body|integer| yes |ユーザーの識別ID|
|» geoFenceId|body|integer| yes |ジオフェンスの識別ID|
|» entryTime|body|string| yes |入場時刻|

> Response Examples

> 401 Response

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|Inline|

### Responses Data Schema

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
|» success|boolean|true|none||none|

## POST 1日の消費カロリーを記録する

POST /api/v1/fitness/save

> Body Parameters

```json
{
  "userId": 1,
  "calories": 100,
  "timestamp": "2024-10-25T04:58:10.391Z"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|body|body|object| no |none|
|» userId|body|integer| yes |none|
|» calories|body|number(float)| yes |none|
|» timestamp|body|string| yes |none|

> Response Examples

> 200 Response

```json
{}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

## GET フィットネス情報を取得

GET /api/v1/fitness

> Body Parameters

```json
{
  "userId": 62795097
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|Authorization|header|string| no |none|
|body|body|object| no |none|
|» userId|body|integer| yes |none|

> Response Examples

> 200 Response

```json
{}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### Responses Data Schema

# Data Schema


## データベースの設計
