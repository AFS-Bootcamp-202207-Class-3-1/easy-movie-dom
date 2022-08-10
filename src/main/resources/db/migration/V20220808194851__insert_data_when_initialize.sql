
INSERT INTO actor_movie_relation (id, actor_id, movie_id) VALUES (1, 1, 1);
INSERT INTO actor_movie_relation (id, actor_id, movie_id) VALUES (2, 2, 1);
INSERT INTO actor_movie_relation (id, actor_id, movie_id) VALUES (3, 3, 1);
INSERT INTO actor_movie_relation (id, actor_id, movie_id) VALUES (4, 4, 1);


INSERT INTO director_movie_relation (id, director_id, movie_id) VALUES (1, 1, 1);


INSERT INTO figure (id, create_time, img_url, name, update_time) VALUES (1, '2022-08-08 19:35:17', 'https://p1.meituan.net/movie/feea9fdcf930fe52f7c2a075db90bc77195799.jpg@128w_170h_1e_1c', '吴京', '2022-08-08 19:36:09');
INSERT INTO figure (id, create_time, img_url, name, update_time) VALUES (2, '2022-08-08 19:36:46', 'https://p1.meituan.net/moviemachine/199bb4af3a73f0e7a974aa135324de32175054.jpg@464w_644h_1e_1c', '弗兰克·格里罗', '2022-08-08 19:36:59');
INSERT INTO figure (id, create_time, img_url, name, update_time) VALUES (3, '2022-08-08 19:37:28', 'https://p1.meituan.net/moviemachine/be4dda3cbbdd868f653e0a3256a8c6d0172681.jpg@464w_644h_1e_1c', '吴刚', '2022-08-08 19:37:26');
INSERT INTO figure (id, create_time, img_url, name, update_time) VALUES (4, '2022-08-08 19:37:43', 'https://p0.meituan.net/moviemachine/dfce8c0cadf9b5439113cfac9b5717f1189629.jpg@464w_644h_1e_1c', '张翰', '2022-08-08 19:37:51');
INSERT INTO figure (id, create_time, img_url, name, update_time) VALUES (5, '2022-08-08 19:37:43', 'https://p0.meituan.net/movie/7f313afb9baf1a6df0b318d02cacf01a204201.jpg@464w_644h_1e_1c', '卢靖姗', '2022-08-08 19:37:51');


INSERT INTO movie (id, box_office, create_time, description, duration, hot, image_url, name, release_country, release_date, release_status, score, types, update_time, english_name) VALUES (1, '1100000', '2022-08-08 15:18:41', '测试1', 120, 100, 'https://scpic.chinaz.net/files/pic/pic9/202009/apic27858.jpg', '战狼2', '中国', '2018-08-08 15:20:21', 1, 5, '动作 战争', '2022-08-08 15:21:10', 'zhanlang2');
INSERT INTO movie (id, box_office, create_time, description, duration, hot, image_url, name, release_country, release_date, release_status, score, types, update_time, english_name) VALUES (2, '1000000', '2022-08-08 15:18:41', '测试2', 120, 100, 'https://scpic.chinaz.net/files/pic/pic9/202009/apic27858.jpg', '战狼3', '中国', '2023-08-08 15:20:21', 1, 5, '动作 战争', '2022-08-08 15:21:10', 'zhanlang2');
INSERT INTO movie (id, box_office, create_time, description, duration, hot, image_url, name, release_country, release_date, release_status, score, types, update_time, english_name) VALUES (3, '100000', '2021-08-08 15:18:41', '测试3', 100, 110, 'https://scpic.chinaz.net/files/pic/pic9/202009/apic27858.jpg', '战狼3', '中国', '2022-08-08 15:20:21', 1, 4, '动作 战争', '2022-08-08 15:21:10', 'zhanlang2');
INSERT INTO movie (id, box_office, create_time, description, duration, hot, image_url, name, release_country, release_date, release_status, score, types, update_time, english_name) VALUES (4, '10000002', '2022-08-08 15:18:41', '测试4', 120, 100, 'https://scpic.chinaz.net/files/pic/pic9/202009/apic27858.jpg', '明日战记', '中国', '2022-08-07 15:20:21', 1, 5, '动作 战争', '2022-08-08 15:21:10', 'Warriors of Future');
INSERT INTO movie (id, box_office, create_time, description, duration, hot, image_url, name, release_country, release_date, release_status, score, types, update_time, english_name) VALUES (5, '1100000', '2022-08-08 15:18:41', '测试5', 90, 102, 'https://p0.pipi.cn/mmdb/25bfd6d72c992367cb537c020675f703a7045.jpg?imageView2/1/w/464/h/644', '独行月球', '中国', '2022-08-08 15:20:21', 1, 5, '动作 战争', '2022-08-08 15:21:10', 'zhanlang2');
INSERT INTO movie (id, box_office, create_time, description, duration, hot, image_url, name, release_country, release_date, release_status, score, types, update_time, english_name) VALUES (6, '999999', '2022-08-08 15:18:41', '测试6', 80, 109, 'https://p0.pipi.cn/mmdb/25bfd63371ff2a8ea32c956e191892497fdd4.jpg?imageView2/1/w/464/h/644', '猪猪侠大电影：海洋日记', '中国', '2022-08-08 15:20:21', 1, 5, '动作 战争', '2022-08-08 15:21:10', 'GG BOND：Ocean Mission');
INSERT INTO movie (id, box_office, create_time, description, duration, hot, image_url, name, release_country, release_date, release_status, score, types, update_time, english_name) VALUES (7, '9', '2021-08-08 15:18:41', '测试7', 99, 89, 'https://p0.pipi.cn/mmdb/25bfd6d771f0fa57e267cb131f671a5889b2d.jpg?imageView2/1/w/464/h/644', '神探大战', '中国', '2022-08-08 15:20:21', 1, 4, '动作 战争', '2022-08-08 15:21:10', 'Detective VS. Sleuths');
INSERT INTO movie (id, box_office, create_time, description, duration, hot, image_url, name, release_country, release_date, release_status, score, types, update_time, english_name) VALUES (8, '200', '2022-08-08 15:18:41', '测试8', 89, 90, 'https://p0.pipi.cn/mmdb/25bfd671339c7e8ea33139d0476cb0d92908d.jpg?imageView2/1/w/464/h/644', '人生大事', '中国', '2022-08-07 15:20:21', 1, 5, '动作 战争', '2022-08-08 15:21:10', 'Warriors of Future');
INSERT INTO movie (id, box_office, create_time, description, duration, hot, image_url, name, release_country, release_date, release_status, score, types, update_time, english_name) VALUES (9, '1100000', '2022-08-08 15:18:41', '测试1', 120, 100, 'https://p0.pipi.cn/mmdb/25bfd63387a0fab86087a91e516d703475563.jpg?imageView2/1/w/464/h/644', '断·桥', '中国', '2018-08-08 15:20:21', 0, 5, '动作 战争', '2022-08-08 15:21:10', 'The Fallen Bridge');
INSERT INTO movie (id, box_office, create_time, description, duration, hot, image_url, name, release_country, release_date, release_status, score, types, update_time, english_name) VALUES (10, '1000000', '2022-08-08 15:18:41', '测试2', 120, 100, 'https://p0.pipi.cn/mmdb/25bfd63302f51b71f731394af61386d5b9f18.jpg?imageView2/1/w/464/h/644', '新神榜：杨戬', '中国', '2023-08-08 15:20:21', 0, 5, '动作 战争', '2022-08-08 15:21:10', 'xinshenbang');


INSERT INTO theater (id, address, create_time, description, image_url, name, phone_number, update_time) VALUES (1, '珠海市香洲区南方软件园', '2022-08-08 15:36:27', '测试', 'https://static2.17youhui.com.cn/uploads/sites/31/2021/12/26a647063ce46d291bfbfa7b5d2f1f8d.jpg', 'Coffee Studio', '110', '2022-08-08 15:37:02');

INSERT INTO theater_movie_relation (id, movie_id, theater_id) VALUES (1, 1, 1);
INSERT INTO theater_movie_relation (id, movie_id, theater_id) VALUES (2, 2, 1);


INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time) VALUES (1, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 55, '2022-08-08 20:48:08', 1, '2022-08-08 20:48:12');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time) VALUES (2, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 2, 100, '2022-08-08 20:48:08', 1, '2022-08-08 20:48:12');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time) VALUES (3, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 50, '2022-08-08 22:48:08', 1, '2022-08-08 20:48:12');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time) VALUES (4, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 51, '2022-08-09 20:48:08', 1, '2022-08-08 20:48:12');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time) VALUES (5, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 52, '2022-08-09 22:48:08', 1, '2022-08-08 20:48:12');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time) VALUES (6, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 53, '2022-08-10 20:48:08', 1, '2022-08-08 20:48:12');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time) VALUES (7, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 54, '2022-08-10 22:48:08', 1, '2022-08-08 20:48:12');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time) VALUES (8, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 59, '2022-08-11 20:48:08', 1, '2022-08-08 20:48:12');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time) VALUES (9, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 65, '2022-08-11 22:48:08', 1, '2022-08-08 20:48:12');
INSERT INTO schedule (id, create_time, end_time, movie_id, price, start_time, theater_id, update_time) VALUES (10, '2022-08-08 20:47:56', '2022-08-08 22:47:57', 1, 63, '2022-08-12 20:48:08', 1, '2022-08-08 20:48:12');
