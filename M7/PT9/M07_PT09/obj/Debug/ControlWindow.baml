   M S B A M L   `   `   `  ����C  ?M07_PT09, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null#clr-namespace:M07_PT09M07_PT09  5   	       M07_PT09.ControlWindow   5   	   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   (localclr-namespace:M07_PT09     5   	   -   5          � WindowStartupLocation$  CenterScreen=�5   	    � Title$  ��5	   	   $	��620��6   $
��1024��6    � 
ResizeMode$ NoResize=�6,   .��5
      �  � ColumnDefinitions 5   
   �� 5      .+�5   
   �� -   5         ��Name$ cancelar��5      $��Cancelar��6#   $
��Left=�66   $��	10,10,0,0q�6Q   $	��Top=�6d   $	��142��6|   $��90��6�   ���	#FFFF9E9E$��36(�6�   6�   �� 5   
   -   5        $ guardar��5      $��Guardar��6"   $
��Left=�64   $��
174,10,0,0q�6O   $	��Top=�6c   $	��142��6{   $��90��6�   ���	#FFABFF9E$��36(�6�   �� 5   
   -   5        $ eliminar��5      $��Eliminar��6#   $
��Left=�66   $��
700,10,0,0q�6Q   $	��Top=�6e   $	��142��6}   $��90��6�   ���	#FFFF9E9E$��36(�6�   �� 5   
   -   5        $	 add��5      $��Añadir��6   $
��Left=�6/   $��
856,10,0,0q�6J   $	��Top=�6^   $	��142��6v   $��90��6�   ���	#FFABFF9E$��36(�6�   �� 5   
   
���   �$��2q�6%   $
��Left=�69   $��90��6T   $��
338,10,0,0q�6`   $	��Top=�6t   $	��342��6�   �� 5   
   $
��MESA��6   $��72(�6   $��338,0,453,491q�6-   $
��Bold"�6D   �� 5   
   -   5         ��Name$ 	view_mesa��5      $��00��6#   $��72(�60   $��568,0,347,491q�6>   $
��Bold"�6U   �� 5   
   -   5        $ mas_cantidad��5      $��+��6'   $
��Left=�63   $��856,135,0,0q�6N   $	��Top=�6c   $	��142��6{   $��90��6�   ���	#FFB1D4FF$��72(�6�   �� 5   
   -   5        $ menos_cantidad��5      $��-��6)   $
��Left=�65   $��856,230,0,0q�6P   $	��Top=�6e   $	��142��6}   $��90��6�   ���	#FFB1D4FF$��72(�6�   �� 5   
   -	   5        $ recibo��5      $��Recibo��6!   $
��Left=�62   $��856,489,0,0q�6M   $	��Top=�6b   $	��142��6z   $��90��6�   ���	#FFABFF9E$��36(�6�   �� 5   
   
���   �$��1q�6%   $
��Left=�69   $	��159��6T   $��856,325,0,0q�6a   $	��Top=�6v   $	��142��6�   �� 5   
   $��Total:��6   $��48(�6!   $��856,325,18,194q�6/   $
��Bold"�6G   �� 5   
   -
   5        $ total��5      $��00,00€��6   $��36(�60   $��856,395,18,124q�6>   $
��Bold"�6V   &   System.Windows.Controls.DataGrid  5   
   -   5          Name$ datos��5      $
��Left=�6"   $	��444��6=   $��
10,135,0,0q�6J   $	��Top=�6^   $	��832��6v   5      5      