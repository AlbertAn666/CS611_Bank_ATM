
drop table account;

drop table customer;

select * from (
                  select distinct transaction.*
                  from transaction
                           join (select * from account where account.customer_id = 10) as account_of_the_customer on
                      (transaction.to_account_id = account_of_the_customer.id or
                       transaction.from_account_id = account_of_the_customer.id)
              ) as transaction_of_customer left join ;





select distinct * from transaction
    join account as to_account_of_the_customer on transaction.to_account_id=to_account_of_the_customer.id
    join account as from_account_of_the_customer on transaction.from_account_id=from_account_of_the_customer.id;









