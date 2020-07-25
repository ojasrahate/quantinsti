require 'selenium-webdriver'
require "test/unit/assertions"
include Test::Unit::Assertions

driver = Selenium::WebDriver.for :chrome
driver.get "https://quantra.quantinsti.com/"

login = driver.find_element(:css , '.item.signup-link')
login.click
sleep 5
driver.find_element(:name , 'email').send_keys  ENV['email']
driver.find_element(:name , 'password').send_keys  ENV['password']
driver.find_element(:xpath , "//button[@type='submit']").click
sleep 10
driver.find_element(:css , ".item.menu__link--toggle").click
course_list = driver.find_elements(:css , ".subnav-type__list-item")
course_list.each do |course_element|
   course_element.click if course_element.text == "Sentiment Analysis in Trading"
end
sleep 5
begin
  driver.find_element(:css , ".push-action").click
rescue
  nil
end
course_text = driver.find_element(:xpath , "//div//h1").text.split(": ")[-1] #can get this directly as mentioned in problem but just as a cross verify

price_text = driver.find_element(:css , ".course-detail__data-unit.price-data-unit").text
price_text = price_text.split[-1]
puts "***************************************************************"
puts "Effective Price: #{price_text}"
puts "***************************************************************"
driver.find_element(:css , ".default-slot").click
sleep 5
begin
  driver.find_element(:css , ".push-action").click
rescue
  nil
end
cart_items = driver.find_elements(:css , ".cart-item-title")

cart_items.each do |item|
  puts item.text
end
cart_count = driver.find_element(:css , ".cart-count").text
puts "***************************************************************"
puts "Cart Items Count: #{cart_items.count}"
puts "Cart Count: #{cart_count}"
puts "***************************************************************"

assert_equal( cart_items.count, cart_count.to_i, failure_message = "No match" )

cart_summary_amount = driver.find_elements(:css , ".cart-summary-left")
cart_summary_cost_num =  driver.find_elements(:css , ".cart-summary-right")


for i in 0..(cart_summary_amount.count - 1)
  if cart_summary_amount[i].text == 'Base Amount'
    base_cost = cart_summary_cost_num[i].text
  elsif cart_summary_amount[i].text == 'Amount Payable'
    amount_payable_cost = cart_summary_cost_num[i].text
  end
end
puts "***************************************************************"
puts "Base Amount :#{base_cost}"
puts "Amount Payable#{amount_payable_cost}"
puts "***************************************************************"
sleep 5

driver.find_element(:css , ".cart-item-cta").click
windows = driver.window_handles
driver.switch_to.window windows[1]
driver.close
driver.switch_to.window windows[0]

driver.find_element(:xpath , "//div[contains(@class, 'cart-item-cta')]//a[@href='javascript:void(0);']").click
sleep 2


delete_message = driver.find_element(:css , ".toasted.toasted-primary.info").text
puts "***************************************************************"
puts "Delete Message: #{delete_message}"
puts "***************************************************************"

driver.find_element(:css , ".default-slot").click
driver.find_element(:css , ".form-control.text-uppercase.personal-coupon-input").send_keys "ABC"

driver.find_element(:xpath , "//div[contains(@class, 'coupon-form__button')]//span[contains(@class, 'default-slot')]").click

coupon_alert_text = driver.find_element(:css , ".coupon-alert-box").text
puts "***************************************************************"
puts "Coupon Error: #{coupon_alert_text}"
puts "***************************************************************"
driver.find_element(:css , ".close").click
driver.find_element(:css , ".profile-pic-initials").click
sleep 5
sign_out = driver.find_elements(:xpath , "//li[contains(@class, 'logout')]//a[@href='javascript:void(0);']")
sign_out[1].click
