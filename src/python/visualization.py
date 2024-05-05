import mysql.connector
import matplotlib.pyplot as plt
from io import BytesIO
import base64

# Connect to the MySQL database
db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="password",
    database="habit_tracker"
)

# Retrieve habit data from the database
cursor = db.cursor()
cursor.execute("SELECT name, streak FROM habits")
habit_data = cursor.fetchall()

# Generate a bar chart visualization
names = [habit[0] for habit in habit_data]
streaks = [habit[1] for habit in habit_data]

plt.figure(figsize=(10, 6))
plt.bar(names, streaks)
plt.xlabel("Habit", fontsize=12)
plt.ylabel("Streak", fontsize=12)
plt.title("Habit Streaks", fontsize=16)
plt.xticks(rotation=45, ha="right", fontsize=10)
plt.tight_layout()

# Save the plot as a PNG image
buffer = BytesIO()
plt.savefig(buffer, format="png")
buffer.seek(0)
image_png = buffer.getvalue()
buffer.close()

# Encode the PNG image as a base64 string
image_base64 = base64.b64encode(image_png).decode("utf-8")

# Print the base64 string to be used in the JSP
print(f"<img src='data:image/png;base64,{image_base64}' alt='Habit Visualization'>")