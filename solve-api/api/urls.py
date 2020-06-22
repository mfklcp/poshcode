from django.urls import path
from . import views

app_name = 'solve'
urlpatterns = [
    path('solve/<int:resposta_id>',views.solve,name="solve_resposta")
]
