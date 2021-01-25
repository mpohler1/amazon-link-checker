# amazon-link-checker
The first version of this program is finished. The plan is to make a cron jobs on my backend that run this program once
a day. That way I'm not having to manually check for availability of products I want.

The steps this program takes to accomplish this are:
1. Parse the command line arguments and model the task
2. Check the product page link for an 'add to cart' button
3. If there is an 'add to cart' button, leverage my Mail API to email myself that the product is in stock
