INSERT INTO account_entity (id, account_no, branch_id, currency_id, name, opener_user_id, start_date, status, version)  VALUES
(2,'2382932', '1', '1', 'test', '1', '2019-02-05 22:08:28.097832	', '1', '0'),
(3,'2382932', '1', '1', 'test', '1', '2019-02-05 22:08:28.097832	', '1', '0');

INSERT INTO investment_account_entity (id)  VALUES (2),(3);
INSERT INTO short_investment_account_entity (id)  VALUES (2),(3);
INSERT INTO account_owner_entity (account_id, client_id, subscription_rate)  VALUES (2, 2, 100),(3, 2, 100);