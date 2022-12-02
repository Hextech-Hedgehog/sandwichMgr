
select * from orders, bill where cast(extract(MONTH from bdate) as int)=:month and cast(extract(Year from bdate) as int)=:year and orders.o_bid = bill.bid and orders.odate=:date;



select * from shop where shid in (select st_shid from sandwichtype where stid=:id);