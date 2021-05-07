[ссылка на задание](https://github.com/netology-code/aqa-homeworks/tree/master/sql#%D0%B7%D0%B0%D0%B4%D0%B0%D1%87%D0%B0-1---%D1%81%D0%BA%D0%BE%D1%80%D0%BE-deadline)

**Перед повторным запуском SUT мне понадобилось запустить:**

1. команду `docker-compose down`

1. контейнер mySql с флагом -V: `docker-compose up -V`

1. в консоли Database - скрипт из файла Schema.sql

**Для запуска SUT была использована команда:**

`java -jar app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/appdb -P:jdbc.user=user -P:jdbc.password=pass`
