import pymysql
import random
from datetime import datetime, timedelta
import numpy as np


class AttendanceGenerator:
    def __init__(self):
        self.MYSQL_HOST = 'localhost'
        self.MYSQL_DB = 'spring_test'
        self.MYSQL_USER = 'root'
        self.MYSQL_PWD = DB_PASSWORD
        self.MTSQL_TABLE = 'test'

        self.connect = pymysql.connect(
            host=self.MYSQL_HOST,
            db=self.MYSQL_DB,
            port=3306,
            user=self.MYSQL_USER,
            password=self.MYSQL_PWD,
            charset='utf8',
            use_unicode=True
        )
        self.cursor = self.connect.cursor()

    def close_connection(self):
        self.cursor.close()
        self.connect.close()

    def generate_random_time(self, base_time_str, mu=0, sigma=10):
        """
        根据基础时间和正态分布生成随机时间偏移。
        :param base_time_str: 基础时间，格式为 'HH:MM'
        :param mu: 正态分布的均值（分钟偏移）
        :param sigma: 正态分布的标准差（分钟偏移）
        :return: 返回一个包含偏移后的时间字符串，格式为 'YYYY-MM-DD HH:MM:SS'
        """
        base_time = datetime.strptime(base_time_str, '%H:%M')
        random_offset = random.gauss(mu, sigma)  # 正态分布偏移（单位分钟）
        new_time = base_time + timedelta(minutes=random_offset)

        return new_time.strftime('%H:%M:%S')

    def generate_attendance_for_day(self, student_id, date):
        """
        生成一个学生一天的打卡记录
        :param student_id: 学生的id
        :param date: 该天的日期，格式为 'YYYY-MM-DD'
        :return: 一个包含六个打卡时间的列表，每个时间为字符串
        """
        base_times = ['08:20', '11:40', '14:20', '17:40', '19:20', '21:40']
        attendance = []

        for base_time in base_times:
            random_time = self.generate_random_time(base_time)
            full_datetime = f'{date} {random_time}'
            attendance.append(full_datetime)

        return attendance

    def insert_attendance(self, student_id, attendance_dates):
        """
        向数据库插入学生的考勤记录
        :param student_id: 学生ID
        :param attendance_dates: 日期范围（列表） ['2024-01-01', '2024-01-02', ...]
        """
        for date in attendance_dates:
            attendance = self.generate_attendance_for_day(student_id, date)
            for time in attendance:
                # 插入数据到数据库
                query = f"INSERT INTO {self.MTSQL_TABLE} (id, update_at) VALUES (%s, %s)"
                self.cursor.execute(query, (student_id, time))

    def generate_date_range(self, start_date, end_date):
        """
        生成一个日期范围，返回从开始日期到结束日期的所有日期
        :param start_date: 起始日期，格式 'YYYY-MM-DD'
        :param end_date: 结束日期，格式 'YYYY-MM-DD'
        :return: 日期范围列表 ['2024-01-01', '2024-01-02', ...]
        """
        start = datetime.strptime(start_date, '%Y-%m-%d')
        end = datetime.strptime(end_date, '%Y-%m-%d')
        delta = timedelta(days=1)
        dates = []

        while start <= end:
            dates.append(start.strftime('%Y-%m-%d'))
            start += delta

        return dates

    def generate_and_insert_attendance(self, student_id, start_date, end_date):
        """
        生成学生的考勤数据并插入到数据库
        :param student_id: 学生ID
        :param start_date: 起始日期，格式 'YYYY-MM-DD'
        :param end_date: 结束日期，格式 'YYYY-MM-DD'
        """
        attendance_dates = self.generate_date_range(start_date, end_date)
        self.insert_attendance(student_id, attendance_dates)
        self.connect.commit()  # 提交到数据库


if __name__ == '__main__':
    # 初始化类
    generator = AttendanceGenerator()

    # 生成并插入数据
    for student_id in range(1, 100): # 假设学生ID为1
        start_date = '2024-01-01'
        end_date = '2024-09-30'
        generator.generate_and_insert_attendance(student_id, start_date, end_date)

    # 关闭数据库连接
    generator.close_connection()
    print("考勤数据已成功插入到数据库。")
