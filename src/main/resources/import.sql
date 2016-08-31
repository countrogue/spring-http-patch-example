-- Sample Data

INSERT into location(id,name) values('canada', 'Canada');
INSERT into location(id,name) values('newzealand', 'New Zealand');
INSERT into location(id,name) values('philippines', 'Philippines');

INSERT into asset(id,name,date_acquired,count) values('1','Computer X', CURRENT_TIMESTAMP, 100);
INSERT into asset(id,name,date_acquired,count) values('2','Database Server', CURRENT_TIMESTAMP, 0);
INSERT into asset(id,name,date_acquired,count) values('3','Christmas Tree', CURRENT_TIMESTAMP, 0);

INSERT into asset_location(asset_id,location_id) values('1', 'canada');
INSERT into asset_location(asset_id,location_id) values('1', 'newzealand');
INSERT into asset_location(asset_id,location_id) values('1', 'philippines');
INSERT into asset_location(asset_id,location_id) values('2', 'philippines');
