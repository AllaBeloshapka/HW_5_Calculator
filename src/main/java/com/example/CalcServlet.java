package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;

//Анотация, которая прописывает URL-мэппинг,
// чтобы TomCat мог решить какой сервлет вызвать
@WebServlet("/calc")

//Наследуемся от класса HttpServlet, чтобы уметь: слушать порт, читать HTTP-заголовки,
//разбирать протокол, формировать ответ
public class CalcServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,     //request - переменная, которая хранит объекты, полученные от браузера
                          HttpServletResponse response)   //response - переменная, которая хранит объект, через который мы формируем и отправляем ответ браузеру.
            throws IOException {

        response.setContentType("text/html");  //настройка формата ответ
// Пишем класс из Java, который умеет: писать текст, отправлять строки, делать println()
        PrintWriter answer = response.getWriter();  //вызываем getWriter(), чтобы сервер создал поток вывода

                //.parseInt() - т.к. от браузера получаем данные с типом String,
                // а для вычислений нужен int, то этот метод переводит строку в число
        int num1 = Integer.parseInt(request.getParameter("num1"));
        //.getParameter() - метод, который ищет параметр из формы HTML с "именем"
                // и возвращает его значение
        int num2 = Integer.parseInt(request.getParameter("num2"));
        //создаём переменную
        String op = request.getParameter("operation");

        int result = 0;

        //математическая операция
        switch (op) {
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "*": result = num1 * num2; break;
            case "/": result = num1 / num2; break;
        }

        //возвращаем результат
        answer.println("<h2>Результат: " + result + "</h2>");
        //возможность вернуться
        answer.println("<a href='calc.html'>Назад</a>");
    }
}

