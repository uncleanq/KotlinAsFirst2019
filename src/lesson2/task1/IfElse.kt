@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    return when {
        ((age > 10) and (age < 20)) or ((age % 100 > 10) and (age % 100 < 20)) or (age % 10 == 0) or (age % 10 == 5) or (age % 10 == 6) or (age % 10 == 7) or (age % 10 == 8) or (age % 10 == 9) -> ("$age лет")
        (age % 10 == 1) -> ("$age год")
        else -> ("$age года")
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s = (t1 * v1 + t2 * v2 + v3 * t3) / 2
    return when {
        (t1 * v1 >= s) -> s / v1
        ((t1 * v1 + t2 * v2) >= s) -> t1 + (s - t1 * v1) / v2
        else -> t1 + t2 + (s - t1 * v1 - t2 * v2) / v3
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    return when {
        (kingX != rookX1) and(kingX != rookX2) and (kingY != rookY1) and(kingY != rookY2) -> 0
        (kingX == rookX1) or (kingY == rookY1) and (kingX != rookX2) and (kingY != rookY2) -> 1
        (kingX == rookX2) or (kingY == rookY2) and (kingX != rookX1) and (kingY != rookY1) -> 2
        else -> 3 // переписал с else 3. таку удобнее выделить чего не должно быть в "1" и "2"
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    return when {
        ((abs(bishopX - kingX)) == (abs(kingY - bishopY))) and ((kingX == rookX) or (kingY == rookY)) -> 3
        ((abs(bishopX - kingX)) == (abs(kingY - bishopY))) -> 2
        (kingX == rookX) or (kingY == rookY) -> 1
        else -> 0
    }

}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val max1 = max(a, b)
    if (((a + b) < c) or ((a + c < b)) or ((b + c < a))) {
        return -1
    } else {
        when (max(max1, c)) {
            c -> return when {
                c * c < (a * a + b * b) -> 0
                c * c == (a * a + b * b) -> 1
                else -> 2
            }
            a -> return when {
                a * a < (c * c + b * b) -> 0
                a * a == (c * c + b * b) -> 1
                else -> 2
            }
            else -> return when {
                b * b < (c * c + a * a) -> 0
                b * b == (c * c + a * a) -> 1
                else -> 2
            }
        }
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when {
        (a > c) and (a > d) -> -1
        (c > a) and(c > b) -> -1
        (a == d) or (b == c) -> 0
        (c <= a) and (b <= d) -> b - a
        (a <= c) and (b <= d) and (c < b) -> b - c
        (c <= a) and (a < d) and (d <= b) -> d - a
        (a <= c) and (d <= b) -> d - c
        else -> -1
    }
}
