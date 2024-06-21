/* ���[�U�[�e�[�u�����쐬 */
CREATE TABLE USERS
(
    user_id INTEGER AUTO_INCREMENT, /*���[�U�[ID*/
    user_email VARCHAR, /*���[���A�h���X*/
    user_password VARCHAR(100) NOT NULL, /*�p�X���[�h*/
    user_name VARCHAR(100) NOT NULL, /*���[�U�[�l�[��*/
    user_img VARCHAR, /*�A�C�R���摜*/
    privacy_flg INTEGER DEFAULT 1, /*���J�E����J�t���O*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�쐬����*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�X�V����*/
    PRIMARY KEY (user_id) 
);

/* ��J�e�S���[�e�[�u�����쐬 */
CREATE TABLE CATEGORYS1
(
    category1_id INTEGER AUTO_INCREMENT, /*��J�e�S���[ID*/
    category1_name VARCHAR(100) NOT NULL, /*��J�e�S���[��*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�쐬����*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�X�V����*/
    PRIMARY KEY (category1_id)
);

/* ���J�e�S���[�e�[�u�����쐬 */
CREATE TABLE CATEGORYS2
(
    category2_id INTEGER AUTO_INCREMENT, /*���J�e�S���[ID*/
    category1_id INTEGER, /*��J�e�S���[ID*/
    category2_name VARCHAR(100) NOT NULL, /*���J�e�S���[��*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�쐬����*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�X�V����*/
    PRIMARY KEY (category2_id) 
);

/* ���r���[�e�[�u�����쐬 */
CREATE TABLE REVIEWS
(
    review_id INTEGER AUTO_INCREMENT, /*���[�U�[ID*/
    category2_id INTEGER, /*���J�e�S���[ID*/
    review_name VARCHAR(100) NOT NULL, /*���r���[��*/
    review_price INTEGER, /*���r���[���i*/
    review_comment VARCHAR(100), /*���r���[�R�����g*/
    user_id INTEGER, /*���[�U�[ID*/
    privacy_flg INTEGER DEFAULT 1, /*���J�E����J�t���O*/
    delete_flg INTEGER DEFAULT 1, /*�폜�t���O*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�쐬����*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�X�V����*/
    PRIMARY KEY (review_id) 
);

/* ���r���[�摜�e�[�u�����쐬 */
CREATE TABLE REVIEWS_IMGS
(
    review_img_id INTEGER AUTO_INCREMENT, /*���r���[�摜ID*/
    review_id INTEGER, /*���r���[ID*/
    review_img VARCHAR, /*���r���[�摜*/
    delete_flg INTEGER DEFAULT 1, /*�폜�t���O*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�쐬����*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�X�V����*/
    PRIMARY KEY (review_img_id) 
);

/* ���r���[���ڃe�[�u�����쐬 */
CREATE TABLE REVIEWS_ITEMS
(
    review_item_id INTEGER AUTO_INCREMENT, /*���r���[����ID*/
    category2_id INTEGER, /*���J�e�S���[ID*/
    review_item1 VARCHAR(100), /*���r���[���ڂP*/
    review_item2 VARCHAR(100), /*���r���[���ڂQ*/
    review_item3 VARCHAR(100), /*���r���[���ڂR*/
    review_item4 VARCHAR(100), /*���r���[���ڂS*/
    review_item5 VARCHAR(100), /*���r���[���ڂT*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�쐬����*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�X�V����*/
    PRIMARY KEY (review_item_id) 
);

/* ���r���[�X�R�A�e�[�u�����쐬 */
CREATE TABLE REVIEWS_SCORES
(
    review_score_id INTEGER AUTO_INCREMENT, /*���r���[�X�R�AID*/
    review_id INTEGER, /*���r���[ID*/
    review_item_id INTEGER, /*���r���[����ID*/
    review_item1_score INTEGER, /*���r���[���ڂP�X�R�A*/
    review_item2_score INTEGER, /*���r���[���ڂQ�X�R�A*/
    review_item3_score INTEGER, /*���r���[���ڂR�X�R�A*/
    review_item4_score INTEGER, /*���r���[���ڂS�X�R�A*/
    review_item5_score INTEGER, /*���r���[���ڂT�X�R�A*/
    score_avg INTEGER, /*�X�R�A����*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�쐬����*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�X�V����*/
    PRIMARY KEY (review_score_id) 
);

/* �o�b�N�i���o�[�e�[�u�����쐬 */
CREATE TABLE BACKNUMBERS
(
    backnumber_id INTEGER AUTO_INCREMENT, /*�o�b�N�i���o�[ID*/
    review_id INTEGER, /*���r���[ID*/
    backnumber_content VARCHAR(100) NOT NULL, /*�o�b�N�i���o�[�R���e���c*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�쐬����*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�X�V����*/
    PRIMARY KEY (backnumber_id) 
);

/* �t�H���[�e�[�u�����쐬 */
CREATE TABLE FOLLOWS
(
    follow_id INTEGER AUTO_INCREMENT, /*�t�H���[ID*/
    user1_id INTEGER, /*���[�U�[�PID*/
    user2_id INTEGER, /*���[�U�[�QID*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�쐬����*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�X�V����*/
    PRIMARY KEY (follow_id) 
);

/* ���X�g�e�[�u�����쐬 */
CREATE TABLE LIST
(
    list_id INTEGER AUTO_INCREMENT, /*���X�gID*/
    list_name VARCHAR(100) NOT NULL, /*���X�g��*/
    delete_flg INTEGER DEFAULT 1, /*�폜�t���O*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�쐬����*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�X�V����*/
    PRIMARY KEY (list_id) 
);

/* ���X�g���r���[�e�[�u�����쐬 */
CREATE TABLE LIST_REVIEWS
(
    list_review_id INTEGER AUTO_INCREMENT, /*���[�U�[ID*/
    list_id VARCHAR, /*���[���A�h���X*/
    review_id INTEGER(100), /*�p�X���[�h*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�쐬����*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*�X�V����*/
    PRIMARY KEY (list_review_id) 
);

