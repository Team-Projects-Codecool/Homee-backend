INSERT INTO space(id, about, name, version)
VALUES('55d40137-4bfc-4701-ba3a-44f2b7e600cf', 'testAbout', 'testName', 0);

INSERT INTO space(id, about, name, version)
VALUES('e741dad0-ae02-4dec-b895-c356f9795d99', 'testAboutTwo', 'testNameTwo', 0);

INSERT INTO device(id, about, space_id)
VALUES('09c20203-5a20-4c29-ad5e-f46ce3649d33', 'testAbout', '55d40137-4bfc-4701-ba3a-44f2b7e600cf')