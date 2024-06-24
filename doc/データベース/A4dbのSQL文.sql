/* ???[?U?[?e?[?u?????? */
CREATE TABLE USERS
(
    user_id INTEGER AUTO_INCREMENT, /*???[?U?[ID*/
    user_email VARCHAR, /*???[???A?h???X*/
    user_password VARCHAR(100) NOT NULL, /*?p?X???[?h*/
    user_name VARCHAR(100) NOT NULL, /*???[?U?[?l?[??*/
    user_img VARCHAR, /*?A?C?R????*/
    privacy_flg INTEGER DEFAULT 1, /*???J?E????J?t???O*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*??????*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*?X?V????*/
    PRIMARY KEY (user_id) 
);

/* ??J?e?S???[?e?[?u?????? */
CREATE TABLE CATEGORYS1
(
    category1_id INTEGER AUTO_INCREMENT, /*??J?e?S???[ID*/
    category1_name VARCHAR(100) NOT NULL, /*??J?e?S???[??*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*??????*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*?X?V????*/
    PRIMARY KEY (category1_id)
);

/* ???J?e?S???[?e?[?u?????? */
CREATE TABLE CATEGORYS2
(
    category2_id INTEGER AUTO_INCREMENT, /*???J?e?S???[ID*/
    category1_id INTEGER, /*??J?e?S???[ID*/
    category2_name VARCHAR(100) NOT NULL, /*???J?e?S???[??*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*??????*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*?X?V????*/
    PRIMARY KEY (category2_id) 
);

/* ???r???[?e?[?u?????? */
CREATE TABLE REVIEWS
(
    review_id INTEGER AUTO_INCREMENT, /*???[?U?[ID*/
    category2_id INTEGER, /*???J?e?S???[ID*/
    review_name VARCHAR(100) NOT NULL, /*???r???[??*/
    review_price INTEGER, /*???r???[???i*/
    review_comment VARCHAR(100), /*???r???[?R?????g*/
    user_id INTEGER, /*???[?U?[ID*/
    privacy_flg INTEGER DEFAULT 1, /*???J?E????J?t???O*/
    delete_flg INTEGER DEFAULT 1, /*???t???O*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*??????*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*?X?V????*/
    PRIMARY KEY (review_id) 
);

/* ???r???[???e?[?u?????? */
CREATE TABLE REVIEWS_IMGS
(
    review_img_id INTEGER AUTO_INCREMENT, /*???r???[??ID*/
    review_id INTEGER, /*???r???[ID*/
    review_img VARCHAR, /*???r???[??*/
    delete_flg INTEGER DEFAULT 1, /*???t???O*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*??????*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*?X?V????*/
    PRIMARY KEY (review_img_id) 
);

/* ???r???[????e?[?u?????? */
CREATE TABLE REVIEWS_ITEMS
(
    review_item_id INTEGER AUTO_INCREMENT, /*???r???[????ID*/
    category2_id INTEGER, /*???J?e?S???[ID*/
    review_item1 VARCHAR(100), /*???r???[????P*/
    review_item2 VARCHAR(100), /*???r???[????Q*/
    review_item3 VARCHAR(100), /*???r???[????R*/
    review_item4 VARCHAR(100), /*???r???[????S*/
    review_item5 VARCHAR(100), /*???r???[????T*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*??????*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*?X?V????*/
    PRIMARY KEY (review_item_id) 
);

/* ???r???[?X?R?A?e?[?u?????? */
CREATE TABLE REVIEWS_SCORES
(
    review_score_id INTEGER AUTO_INCREMENT, /*???r???[?X?R?AID*/
    review_id INTEGER, /*???r???[ID*/
    review_item1_score INTEGER, /*???r???[????P?X?R?A*/
    review_item2_score INTEGER, /*???r???[????Q?X?R?A*/
    review_item3_score INTEGER, /*???r???[????R?X?R?A*/
    review_item4_score INTEGER, /*???r???[????S?X?R?A*/
    review_item5_score INTEGER, /*???r???[????T?X?R?A*/
    score_avg INTEGER, /*?X?R?A????*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*??????*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*?X?V????*/
    PRIMARY KEY (review_score_id) 
);

/* ?o?b?N?i???o?[?e?[?u?????? */
CREATE TABLE BACKNUMBERS
(
    backnumber_id INTEGER AUTO_INCREMENT, /*?o?b?N?i???o?[ID*/
    review_id INTEGER, /*???r???[ID*/
    backnumber_content VARCHAR(100) NOT NULL, /*?o?b?N?i???o?[?R???e???c*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*??????*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*?X?V????*/
    PRIMARY KEY (backnumber_id) 
);

/* ?t?H???[?e?[?u?????? */
CREATE TABLE FOLLOWS
(
    follow_id INTEGER AUTO_INCREMENT, /*?t?H???[ID*/
    user1_id INTEGER, /*???[?U?[?PID*/
    user2_id INTEGER, /*???[?U?[?QID*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*??????*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*?X?V????*/
    PRIMARY KEY (follow_id) 
);

/* ???X?g?e?[?u?????? */
CREATE TABLE LIST
(
    list_id INTEGER AUTO_INCREMENT, /*???X?gID*/
    list_name VARCHAR(100) NOT NULL, /*???X?g??*/
    delete_flg INTEGER DEFAULT 1, /*???t???O*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*??????*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*?X?V????*/
    PRIMARY KEY (list_id) 
);

/* ???X?g???r???[?e?[?u?????? */
CREATE TABLE LIST_REVIEWS
(
    list_review_id INTEGER AUTO_INCREMENT, /*???[?U?[ID*/
    list_id VARCHAR, /*???[???A?h???X*/
    review_id INTEGER(100), /*?p?X???[?h*/
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*??????*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, /*?X?V????*/
    PRIMARY KEY (list_review_id) 
);

/* ??J?e?S?? */
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('?????');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('?t?@?b?V????');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('?R?X??');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('??|?E?N???t?g');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('?Q?[??');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('??');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('??d');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('???');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('?H???i');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('????');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('???p?i');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('?f??/???y');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('?T?[?r?X');
INSERT INTO CATEGORYS1 (CATEGORY1_NAME) VALUES ('??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('2', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('2', '?g?b?v?X');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('2', '?C???i?[');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('2', '?A?E?^?[');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('2', '?A?N?Z?T???[');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('2', '?C??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('2', '?C');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('2', '?o?b?O');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('2', '?X?q');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('2', '??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '?`?[?N');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '?A?C?u???E');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '?}?X?J??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '?A?C???C?i?[');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '?A?C?V???h?E');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '?x?[?X???C?N');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '?l?C??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '?X?L???P?A');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '?{?f?B?P?A');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '?w?A?P?A');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('3', '??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('4', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('4', '????');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('4', '???n');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('4', '????E?p?[?c');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('4', '?r?[?Y?E?X?g?[??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('4', '??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('4', '??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('5', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('5', '?e???r?Q?[??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('5', '?A?v???Q?[??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('5', '?p?Y???E?{?[?h?Q?[??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('5', '??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '??????');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '???X?g????/?t?@?~???X');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '?J?t?F/?i???X');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '????????H?X');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '?o?C?L???O');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', 'BAR');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '?e??????');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '?V??/??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '?h??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '???s/???');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '????????');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '???');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '???/????/?a?@');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('6', '??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('7', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('7', '?f????A');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('7', '?I?[?f?B?I??A');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('7', 'AV?A?N?Z?T??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('7', '????d');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('7', '?L?b?`????d');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('7', '??????d');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('7', '???N?E???e??d');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('7', '?G???d');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('7', '??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '?e?[?u??/?f?X?N');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '??q/?\?t?@');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '?{?I');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '?x?b?h');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '?J?[?e??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '???[');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '???');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '?J?[?y?b?g');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '?~???[');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '?G??/?????');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('8', '??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '??????');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '???E???');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '?W???[?X?E?????');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '????q');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '????');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '?f?U?[?g');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '???N');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('9', '??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('10', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('10', '????');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('10', '????');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('10', '????');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('10', '?Q?l??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('10', '?G??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('10', '??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('11', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('11', '????p?i');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('11', '?|???p?i');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('11', '?o?X?p?i');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('11', '?g?C???p?i');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('11', '?????p?i');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('11', '?L?b?Y?p?i');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('11', '?z?r?[');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('11', '???[??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('11', '??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('11', '??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('12', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('12', '?A?j??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('12', '?f??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('12', '?h???}');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('12', '?e???r??g');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('12', '???W?I');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('12', '???y');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('12', '??????');

/* ???J?e?S?? */
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('13', '?????');  
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('13', '?A?v??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('13', '?T?C?g');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('13', '?????[??');
INSERT INTO CATEGORYS2 (CATEGORY1_ID, CATEGORY2_NAME) VALUES ('13', '??????');


