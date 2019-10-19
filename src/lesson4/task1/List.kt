@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import lesson3.task1.isPrime
import lesson3.task1.minDivisor
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var result = 0.0
    return if (v.isEmpty()) result
    else {
        for (i in v) result += sqr(i)
        sqrt(result)
    }
}


/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var result = 0.0
    return if (list.isEmpty()) result
    else {
        for (i in list) result += i
        result / list.size
    }
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mid = mean(list)
    for (i in 0 until list.size)
        list[i] -= mid
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var result = 0
    for (i in a.indices) //почему мне идея не предложила так заменить в прошлом задании?
        result += a[i] * b[i]
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0
    for (i in p.indices)
        result += p[i] * x.toDouble().pow(i).toInt()
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) list[i] += list[i - 1]
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var num = n
    var div = 2
    val result = mutableListOf<Int>()
    do {
        while (num % div == 0) {
            result.add(div)
            num /= div
        }
        div++
    } while (num > 1)

    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var num = n
    val result = mutableListOf<Int>()
    while (num >= 1) {
        result.add(num % base)
        num /= base
    }
    return if (result.isEmpty())
        return listOf(0)
    else result.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()/*{
    var list = convert(n, base)
    val alphabet = mapOf<String>(
        10 to "a",
        11 to "b",
        12 to "c",
        13 to "d",
        14 to "e",
        15 to "f",
        16 to "g",
        17 to "h",
        18 to "i",
        19 to "j",
        20 to "k",
        21 to "l",
        22 to "m",
        23 to "n",
        24 to "o",
        25 to "p",
        26 to "q",
        27 to "r",
        28 to "s",
        29 to "t",
        30 to "u",
        31 to "v",
        32 to 'w',
        33
    )
    var result = mutableListOf<Any>()
    for (number in list)
        if (number < 10)
            result.add(number)
        else result.add(alphabet[n])
    return result.joinToString(" ")
} */

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    val size = digits.size
    for (i in digits.indices) {
        result += (digits[i] * base.toDouble().pow(size - 1 - i)).toInt()//-1 тк степени с 0 считаются
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    TODO()
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var number = n
    var result = ""
    val rome = listOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M").reversed()
    val arab = listOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000).reversed()
    var index = 0
    while (number > 0)
        if (number >= arab[index]) {
            result += (rome[index])
            number -= arab[index]
        } else index++
    return result
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var result = ""
    var count = digitNumber(n) // кол-во цифр
    var num = n
    while (count > 0) {
        if (count == 6) {//берем макс вариант
            when {
                num % 100000 == 1 -> {
                    result += "сто "
                    num %= 100000
                    count--
                }
                num % 100000 == 2 -> {
                    num %= 100000
                    count--
                    result += ("двести ")
                }
                num % 100000 == 3 -> {
                    num %= 100000
                    count--
                    result += ("триста ")
                }
                num % 100000 == 4 -> {
                    num %= 100000
                    count--
                    result += ("четыреста ")
                }
                num % 100000 == 5 -> {
                    num %= 100000
                    count--
                    result += ("пятьсот ")
                }
                num % 100000 == 6 -> {
                    num %= 100000
                    count--
                    result += ("шестьсот ")
                }
                num % 100000 == 7 -> {
                    num %= 100000
                    count--
                    result += ("семьсот ")
                }
                num % 100000 == 8 -> {
                    num %= 100000
                    count--
                    result += ("восемьсот ")
                }
                num % 100000 == 9 -> {
                    num %= 100000
                    count--
                    result += ("девятьсот ")
                }
            }
        }
        if (count == 5) {
            if (num / 1000 in 10..19) {//случай для специальных
                when (num / 1000) {
                    10 -> {
                        count -= 2
                        num %= 1000
                        result += ("десять тысяч ")
                    }
                    11 -> {
                        count -= 2
                        num %= 1000
                        result += ("одинадцать тысяч ")
                    }
                    12 -> {
                        count -= 2
                        num %= 1000
                        result += ("двенадцать тысяч ")
                    }
                    13 -> {
                        count -= 2
                        num %= 1000
                        result += ("тринадцать тысяч ")
                    }
                    14 -> {
                        count -= 2
                        num %= 1000
                        result += ("четырнадцать тысяч ")
                    }
                    15 -> {
                        count -= 2
                        num %= 1000
                        result += ("пятнадцать тысяч ")
                    }
                    16 -> {
                        count -= 2
                        num %= 1000
                        result += ("шестнадцать тысяч ")
                    }
                    17 -> {
                        count -= 2
                        num %= 1000
                        result += ("семнадцать тысяч ")
                    }
                    18 -> {
                        count -= 2
                        num %= 1000
                        result += ("восемнадцать тысяч ")
                    }
                    19 -> {
                        count -= 2
                        num %= 1000
                        result += ("девятнадцать тысяч ")
                    }
                }
            } else {
                when (num / 10000) {
                    2 -> {
                        count--
                        num %= 1000
                        result += ("двадцать ")
                    }
                    3 -> {
                        count--
                        num %= 1000
                        result += ("тридцать ")
                    }
                    4 -> {
                        count--
                        num %= 1000
                        result += ("сорок ")
                    }
                    5 -> {
                        count--
                        num %= 1000
                        result += ("пятьдесят ")
                    }
                    6 -> {
                        count--
                        num %= 1000
                        result += ("шестьдесят ")
                    }
                    7 -> {
                        count--
                        num %= 1000
                        result += ("семьдесят ")
                    }
                    8 -> {
                        count--
                        num %= 1000
                        result += ("восемьдесят ")
                    }
                    9 -> {
                        count--
                        num %= 1000
                        result += ("девяносто ")
                    }
                }
                count--
                num %= 1000
                when (num / 1000) {
                    0 -> {
                        count--
                        num %= 1000
                        result += ("тысяч ")
                    }
                    1 -> {
                        count--
                        num %= 1000
                        result += ("одна тысяча ")
                    }
                    2 -> {
                        count--
                        num %= 1000
                        result += ("две тысячи ")
                    }
                    3 -> {
                        count--
                        num %= 1000
                        result += ("три тысячи ")
                    }
                    4 -> {
                        count--
                        num %= 1000
                        result += ("четыре тысячи ")
                    }
                    5 -> {
                        count--
                        num %= 1000
                        result += ("пять тысяч ")
                    }
                    6 -> {
                        count--
                        num %= 1000
                        result += ("шесть тысяч ")
                    }
                    7 -> {
                        count--
                        num %= 1000
                        result += ("семь тысяч ")
                    }
                    8 -> {
                        count--
                        num %= 1000
                        result += ("восемь тысяч ")
                    }
                    9 -> {
                        count--
                        num %= 1000
                        result += ("девять тысяч ")
                    }
                }
            }
        }
        if (count == 4) {
            when (num / 1000) {
                1 -> {
                    count--
                    num %= 1000
                    result += ("одна тысяча")
                }
                2 -> {
                    count--
                    num %= 1000
                    result += ("две тысячи")
                }
                3 -> {
                    count--
                    num %= 1000
                    result += ("три тысячи")
                }
                4 -> {
                    count--
                    num %= 1000
                    result += ("четыре тысячи")
                }
                5 -> {
                    count--
                    num %= 1000
                    result += ("пять тысяч")
                }
                6 -> {
                    count--
                    num %= 1000
                    result += ("шесть тысяч")
                }
                7 -> {
                    count--
                    num %= 1000
                    result += ("семь тысяч")
                }
                8 -> {
                    count--
                    num %= 1000
                    result += ("восемь тысяч")
                }
                9 -> {
                    count--
                    num %= 1000
                    result += ("девять тысяч")
                }
            }
        }
        if (count == 3) {
            when (n / 100) {
                1 -> {
                    count--
                    num %= 100
                    result += ("сто ")
                }
                2 -> {
                    count--
                    num %= 100
                    result += ("двести ")
                }
                3 -> {
                    count--
                    num %= 100
                    result += ("триста ")
                }
                4 -> {
                    count--
                    num %= 100
                    result += ("четыреста ")
                }
                5 -> {
                    count--
                    num %= 100
                    result += ("пятьсот ")
                }
                6 -> {
                    count--
                    num %= 100
                    result += ("шестьсот ")
                }
                7 -> {
                    count--
                    num %= 100
                    result += ("семьсот ")
                }
                8 -> {
                    count--
                    num %= 100
                    result += ("восемьсот ")
                }
                9 -> {
                    count--
                    num %= 100
                    result += ("девятьсот ")
                }
            }
        }
        if (count == 2) {
            if (num in 10..19) {
                when (num) {
                    10 -> {
                        count -= 2
                        num = 0
                        result += ("десять ")
                    }
                    11 -> {
                        count -= 2
                        num = 0
                        result += ("одинадцать ")
                    }
                    12 -> {
                        count -= 2
                        num = 0
                        result += ("двенадцать ")
                    }
                    13 -> {
                        count -= 2
                        num = 0
                        result += ("тринадцать ")
                    }
                    14 -> {
                        count -= 2
                        num = 0
                        result += ("четырнадцать ")
                    }
                    15 -> {
                        count -= 2
                        num = 0
                        result += ("пятнадцать ")
                    }
                    16 -> {
                        count -= 2
                        num = 0
                        result += ("шестнадцать ")
                    }
                    17 -> {
                        count -= 2
                        num = 0
                        result += ("семнадцать ")
                    }
                    18 -> {
                        count -= 2
                        num = 0
                        result += ("восемнадцать ")
                    }
                    19 -> {
                        count -= 2
                        num = 0
                        result += ("девятнадцать ")
                    }
                }
            } else {
                when (num / 10) {
                    2 -> {
                        count--
                        num %= 10
                        result += ("двадцать ")
                    }
                    3 -> {
                        count--
                        num %= 10
                        result += ("тридцать ")
                    }
                    4 -> {
                        count--
                        num %= 10
                        result += ("сорок ")
                    }
                    5 -> {
                        count--
                        num %= 10
                        result += ("пятьдесят ")
                    }
                    6 -> {
                        count--
                        num %= 10
                        result += ("шестьдесят ")
                    }
                    7 -> {
                        count--
                        num %= 10
                        result += ("семьдесят ")
                    }
                    8 -> {
                        count--
                        num %= 10
                        result += ("восемьдесят ")
                    }
                    9 -> {
                        count--
                        num %= 10
                        result += ("девяносто ")
                    }
                }
                when (num) {
                    1 -> {
                        count--
                        num = 0
                        result += ("один")
                    }
                    2 -> {
                        count--
                        num = 0
                        result += ("два")
                    }
                    3 -> {
                        count--
                        num = 0
                        result += ("три")
                    }
                    4 -> {
                        count--
                        num = 0
                        result += ("четыре")
                    }
                    5 -> {
                        count--
                        num = 0
                        result += ("пять")
                    }
                    6 -> {
                        count--
                        num = 0
                        result += ("шесть")
                    }
                    7 -> {
                        count--
                        num = 0
                        result += ("семь")
                    }
                    8 -> {
                        count--
                        num = 0
                        result += ("восемь")
                    }
                    9 -> {
                        count--
                        num = 0
                        result += ("девять")
                    }
                }
            }
        }
        if (count == 1) {
            when (num) {
                1 -> {
                    count--
                    num = 0
                    result += ("один")
                }
                2 -> {
                    count--
                    num = 0
                    result += ("два")
                }
                3 -> {
                    count--
                    num = 0
                    result += ("три")
                }
                4 -> {
                    count--
                    num = 0
                    result += ("четыре")
                }
                5 -> {
                    count--
                    num = 0
                    result += ("пять")
                }
                6 -> {
                    count--
                    num = 0
                    result += ("шесть")
                }
                7 -> {
                    count--
                    num = 0
                    result += ("семь")
                }
                8 -> {
                    count--
                    num = 0
                    result += ("восемь")
                }
                9 -> {
                    count--
                    num = 0
                    result += ("девять")
                }
            }
        }
    }
    return (result)
}