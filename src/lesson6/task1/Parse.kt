@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.lang.NumberFormatException
import java.lang.String.format

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 *
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val parts = str.split(" ") //делится пробелами
    if (parts.size != 3)
        return ""
    try {
        val year = parts[2].toInt()
        val month = when (parts[1]) {
            "января" -> 1
            "февраля" -> 2
            "марта" -> 3
            "апреля" -> 4
            "мая" -> 5
            "июня" -> 6
            "июля" -> 7
            "августа" -> 8
            "сентября" -> 9
            "октября" -> 10
            "ноября" -> 11
            "декабря" -> 12
            else -> return ""
        }
        val day = parts[0].toInt()
        return if ((daysInMonth(month, year) >= day))
        //да, я правда решил задачу второго лессона чтобы не писать большое условие)
            format("%02d.%02d.%d", day, month, year)
        else
            ""
    } catch (e: NumberFormatException) {
        return ""
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".")
    if (parts.size != 3)
        return ""
    try {
        val year = parts[2]
        val month = when (parts[1]) {
            "01" -> "января"
            "02" -> "февраля"
            "03" -> "марта"
            "04" -> "апреля"
            "05" -> "мая"
            "06" -> "июня"
            "07" -> "июля"
            "08" -> "августа"
            "09" -> "сентября"
            "10" -> "октября"
            "11" -> "ноября"
            "12" -> "декабря"
            else -> return ""
        }
        val day = parts[0].toInt()
        return if ((daysInMonth(parts[1].toInt(), parts[2].toInt()) >= day))
            format("%d %s %s", day, month, year)
        else
            ""
    } catch (e: NumberFormatException) {
        return ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    var result = ""
    val phoned = phone.filter { it != '-' && it != ' ' }//убираю лишние пробелы и чёрточки
    if (phoned.contains('(') || phoned.contains(')')) {
        // если содержатся скобки в заданном выражении(не могу их убрать тк они легальны)
        if (Regex("""^\+?\d*\(\d+\)\d+""").matches(phoned))
        //если начинается с "+", цифры, далее скобка, кол-во цифр (мин 1), скобка, цифры мин 1
            for (num in phoned)
                if (num == '+' || num in '0'..'9')
                    result += num
        // записываю в ресалт значения
    } else
        if (Regex("""^\+?\d+""").matches(phoned))
            for (num in phoned)
                if (num == '+' || num in '0'..'9')
                    result += num
    return result
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var max = -1
    if (Regex("""[^\s\d-%]""").find(jumps) != null)
        return max
    //проверка на ненужные символы
    for (jump in jumps.split(" "))
    //прыжки разделены проблелами
        if (Regex("""\d+[^-%]""").matches(jump))
        //если один прижок являтся числами, но не знаками, то
            max = maxOf(max, jump.toInt())
    return max
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var max = -1
    if (Regex("""[^\s\d-%+]""").find(jumps) != null)
        return max
    //проверка на ненужные символы
    for (num in jumps.split(" ").indices)
    //прыжки разделены проблелами, но теперь между ними еще и плюсы
        if (Regex("""\d+""").matches(jumps.split(" ")[num]) &&
            Regex("""\+""").matches(jumps.split(" ")[num + 1])
        )//если один прижок являтся числами,а след знак это "+"
            max = maxOf(max, jumps.split(" ")[num].toInt())
    return max
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val usable = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', '+', '-')
    require(!Regex("""(^\+)|(- -)|("-\s\+)|(^-)|(^\s)""").containsMatchIn(expression) && expression.isNotEmpty())
    for (words in expression)
        require(words in usable)
    var result = expression.split(" ")[0].toInt()
    if (expression.split(" ").size == 1)
        return result
    else
        for (num in 2 until expression.split(" ").size step 2)
        //топаю по числам, первое число добавил
        //забыл, что теперь я прошлое значение смотрю, поэтому indices нельзя юзать, как в проглой
        //смотрю на прошлый знак и в зависимости от этого действую
            if (Regex("""\+""").matches(expression.split(" ")[num - 1]))
                result += expression.split(" ")[num].toInt()
            else if (Regex("""-""").matches(expression.split(" ")[num - 1])
            ) result -= expression.split(" ")[num].toInt()
    return result
}


/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int = TODO()

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String = TODO()


fun phone(text: String): String {
    require(!Regex("""^-""").containsMatchIn(text))
    require(!Regex("""--""").containsMatchIn(text))
    require(!Regex("""[а-я]|(ё)""").containsMatchIn(text))
    val string = StringBuilder()
    val tex = text.toLowerCase()
    val one = listOf('a', 'b', 'c', '2')
    val two = listOf('d', 'e', 'f', '3')
    val three = listOf('g', 'h', 'i', '4')
    val four = listOf('j', 'k', 'l', '5')
    val five = listOf('m', 'n', 'o', '6')
    val six = listOf('p', 'q', 'r', 's', '7')
    val seven = listOf('t', 'u', 'v', '8')
    val eight = listOf('w', 'x', 'y', 'z', '9')
    for (char in tex) {
        when {
            one.contains(char) -> string.append(one.last())
            two.contains(char) -> string.append(two.last())
            three.contains(char) -> string.append(three.last())
            four.contains(char) -> string.append(four.last())
            five.contains(char) -> string.append(five.last())
            six.contains(char) -> string.append(six.last())
            seven.contains(char) -> string.append(seven.last())
            eight.contains(char) -> string.append(eight.last())
            char == '-' -> string.append('-')
            char == '0' -> string.append('0')
            else -> string.append('1')
        }
    }
    return string.toString()
}

fun train(from: String, to: String, route: String): String {
    val dest = route.split(Regex("""(;\s+)""")).toMutableList()
    for (i in dest)
        if (i.isEmpty())
            dest -= i
    var lastH = -1
    var lastM = -1
    var dep = -1
    var arr = -1
    //избегаю 00:00
    var timeToMin = -1
    var depart = false
    var arrival = false
    for (i in dest) {
        val kus = i.trim(';').split(Regex("""\s+"""))
        require(kus.size == 2)// формат "место время"
        require(Regex("""([0-9]{2}:[0-9]{2})""").matches(kus[1]))
        val time = kus[1].split(':')
        require((time[0].toInt() in 0..23 && time[1].toInt() in 0..59))
        //совпадение формата времени

        if (kus[0] == from) {
            lastH = time[0].toInt()
            lastM = time[1].toInt()
            dep = time[0].toInt() * 60 + time[1].toInt()
            depart = true
            continue
            //время отправления
        }
        if (kus[0] == to) {
            require(
                (time[0].toInt() > lastH
                        || (time[0].toInt() == lastH && time[1].toInt() > lastM))
            )
            //требую шобы прошлые часы были меньше новых
            if (depart) {
                arr = time[0].toInt() * 60 + time[1].toInt()
                arrival = true
                timeToMin = arr - dep
            }
        }

    }

    require(depart && arrival)
    var hour = 0 // используем в качестве счета часов
    var minutes = 0 // используем в качестве счета минут
    while (timeToMin > 0) {
        when {
            timeToMin >= 60 -> {
                hour++
                timeToMin -= 60
            }
            else -> {
                minutes++
                timeToMin --
            }
        }
    }
    return if (hour != 0) ("$hour час $minutes минут")
    else ("$minutes мин")
}


/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
