/* ユーザーテーブルを作成 */
CREATE TABLE USERS
(
    user_id INTEGER AUTO_INCREMENT, /*ユーザーID*/
    user_email VARCHAR, /*メールアドレス*/
    user_password INTEGER(100) NOT NULL, /*パスワード*/
    user_name VARCHAR(100) NOT NULL, /*ユーザーネーム*/
    user_img VARCHAR, /*アイコン画像*/
    privcy_flg INTEGER DEFAULT 1, /*公開・非公開フラグ*/
    created_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*作成日時*/
    updated_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*更新日時*/
    PRIMARY KEY (user_id) 
);

/* 大カテゴリーテーブルを作成 */
CREATE TABLE CATEGORYS1
(
    category1_id INTEGER AUTO_INCREMENT, /*大カテゴリーID*/
    category1_name VARCHAR(100) NOT NULL, /*大カテゴリー名*/
    created_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*作成日時*/
    updated_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*更新日時*/
    PRIMARY KEY (category1_id)
);

/* 小カテゴリーテーブルを作成 */
CREATE TABLE CATEGORYS2
(
    category2_id INTEGER AUTO_INCREMENT, /*小カテゴリーID*/
    category1_id INTEGER, /*大カテゴリーID*/
    category2_name VARCHAR(100) NOT NULL, /*小カテゴリー名*/
    created_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*作成日時*/
    updated_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*更新日時*/
    PRIMARY KEY (category2_id) 
);

/* レビューテーブルを作成 */
CREATE TABLE REVIEWS
(
    review_id INTEGER AUTO_INCREMENT, /*ユーザーID*/
    category2_id INTEGER, /*小カテゴリーID*/
    review_name VARCHAR(100) NOT NULL, /*レビュー名*/
    review_price INTEGER, /*レビュー価格*/
    review_comment VARCHAR(100), /*レビューコメント*/
    user_id INTEGER, /*ユーザーID*/
    privacy_flg INTEGER DEFAULT 1, /*公開・非公開フラグ*/
    delete_flg INTEGER DEFAULT 1, /*削除フラグ*/
    created_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*作成日時*/
    updated_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*更新日時*/
    PRIMARY KEY (review_id) 
);

/* レビュー画像テーブルを作成 */
CREATE TABLE REVIEWS_IMGS
(
    review_img_id INTEGER AUTO_INCREMENT, /*レビュー画像ID*/
    review_id INTEGER, /*レビューID*/
    review_img VARCHAR, /*レビュー画像*/
    delete_flg INTEGER DEFAULT 1, /*削除フラグ*/
    created_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*作成日時*/
    updated_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*更新日時*/
    PRIMARY KEY (review_img_id) 
);

/* レビュー項目テーブルを作成 */
CREATE TABLE REVIEWS_ITEMS
(
    review_item_id INTEGER AUTO_INCREMENT, /*レビュー項目ID*/
    category2_id INTEGER, /*小カテゴリーID*/
    review_item1 VARCHAR(100), /*レビュー項目１*/
    review_item2 VARCHAR(100), /*レビュー項目２*/
    review_item3 VARCHAR(100), /*レビュー項目３*/
    review_item4 VARCHAR(100), /*レビュー項目４*/
    review_item5 VARCHAR(100), /*レビュー項目５*/
    created_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*作成日時*/
    updated_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*更新日時*/
    PRIMARY KEY (review_item_id) 
);

/* レビュースコアテーブルを作成 */
CREATE TABLE REVIEWS_SCORES
(
    review_score_id INTEGER AUTO_INCREMENT, /*レビュースコアID*/
    review_id INTEGER, /*レビューID*/
    review_item_id INTEGER, /*レビュー項目ID*/
    review_item1_score INTEGER, /*レビュー項目１スコア*/
    review_item2_score INTEGER, /*レビュー項目２スコア*/
    review_item3_score INTEGER, /*レビュー項目３スコア*/
    review_item4_score INTEGER, /*レビュー項目４スコア*/
    review_item5_score INTEGER, /*レビュー項目５スコア*/
    score_avg INTEGER, /*スコア平均*/
    created_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*作成日時*/
    updated_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*更新日時*/
    PRIMARY KEY (review_score_id) 
);

/* バックナンバーテーブルを作成 */
CREATE TABLE BACKNUMBERS
(
    backnumber_id INTEGER AUTO_INCREMENT, /*バックナンバーID*/
    review_id INTEGER, /*レビューID*/
    backnumber_content VARCHAR(100) NOT NULL, /*バックナンバーコンテンツ*/
    delete_flg INTEGER DEFAULT 1, /*削除フラグ*/
    created_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*作成日時*/
    updated_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*更新日時*/
    PRIMARY KEY (backnumber_id) 
);

/* フォローテーブルを作成 */
CREATE TABLE FOLLOWS
(
    follow_id INTEGER AUTO_INCREMENT, /*フォローID*/
    user1_id INTEGER, /*ユーザー１ID*/
    user2_id INTEGER, /*ユーザー２ID*/
    created_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*作成日時*/
    updated_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*更新日時*/
    PRIMARY KEY (follow_id) 
);

/* リストテーブルを作成 */
CREATE TABLE LIST
(
    list_id INTEGER AUTO_INCREMENT, /*リストID*/
    list_name VARCHAR(100) NOT NULL, /*リスト名*/
    delete_flg INTEGER DEFAULT 1, /*削除フラグ*/
    created_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*作成日時*/
    updated_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*更新日時*/
    PRIMARY KEY (list_id) 
);

/* リストレビューテーブルを作成 */
CREATE TABLE LIST_REVIEWS
(
    list_review_id INTEGER AUTO_INCREMENT, /*ユーザーID*/
    list_id VARCHAR, /*メールアドレス*/
    review_id INTEGER(100), /*パスワード*/
    created_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*作成日時*/
    updated_at VARCHAR DEFAULT 'CURRENT_TIMESTAMP', /*更新日時*/
    PRIMARY KEY (list_review_id) 
);

