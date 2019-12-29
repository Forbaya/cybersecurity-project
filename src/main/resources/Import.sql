INSERT INTO Signup
(Name, Address)
VALUES
('Pekka', 'Mannerheimintie'),
('Marja', 'Sturenkatu');

INSERT INTO User
(Username, Email, Password)
VALUES
('Pekka', 'pekka@maansiirtofirma.fi', 'salasana123'),
('admin', 'ad@min.com', 'admin');

INSERT INTO UserRole
(UserId, Role)
VALUES
(1, 'User'),
(2, 'Admin');