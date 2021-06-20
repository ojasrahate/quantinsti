number = 1000
quotient_three = (number-1)/3
quotient_five = (number-1)/5

total_sum = 0
for i in 1..quotient_three
  total_sum = total_sum + (i * 3)
  multi_five = 5*i   #Saves an operation while addition and if condition below
  total_sum = total_sum + multi_five if i <= quotient_five && multi_five%15 != 0
end

puts total_sum
