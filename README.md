# The-Stable-Matching-problem

This assignment asks you to implement a solution to the stable matching problem. In our case, we will consider the problem of matching coop employers to coop students. In particular, we consider the problem of n employers and n students where each employer will hire a single student. 
Each employer produces a ranking of students according to their preference to hire for the coop term and each student rank all employers according to their preference to work for. Based on these preferences your mission is to write a program that will match each employer with one student such that all players will be as satisfied as possible with their match (in the sense that there will be no incentive for a pair of employer-student that are not matched to each other, to both agree to be matched).
You will be given a list of employers. 

For example : 

0. Thales
1. Canada Post
2. Cisco

And a list of students. For example:

0. Olivia
1. Jackson
2. Sophia


The two lists will always be of the same size.

Students and employers will enter their ranking. For example:

Employer preferences :

0. Thales: 0.Olivia, 1.Jackson, 2.Sophia

1. Canada Post: 2. Sophia, 1.Jackson, 0.Olivia

2. Cisco: 0.Olivia, 2.Sophia, 1.Jackson


Student preferences:

0. Olivia: 0.Thales, 1.Canada Post, 2.Cisco

1. Jackson: 0.Thales, 1.Canada Post, 2.Cisco

2. Sophia: 2.Cisco, 0.Thales, 1.Canada Post

These ranking will be given to you in a text file formatted accordingly.

A stable matching solution for this problem is as follows:

Thales - Olivia

Canada Post - Jackson

Cisco - Sophia

Why is this solution stable? We have to look at the definition of a stable match. A stable match is
defined such that neither party to the match has a preferred party that also prefers them over their current
match. E,g., Canada Post would prefer to hire Olivia over Jackson but Olivia is currently matched with
Thales which she prefers over Canada Post. While Jackson would prefer to work for Thales over Canada
Post but Thales is matched with Olivia which Thales prefers over Jackson. As a result, while neither
Canada Post nor Jackson got their first choice, the match is stable as neither of them have a way to
improve their current match.

As a result, the solution provided is a stable matching and is also perfect. A perfect match is actually a
simpler criterion, just requiring each employer being matched to a student and each student being
matched to an employer.

In summary, in this assignment you will need to find a perfect and stable matching given preference
tables by coop employers and by students. There will always be the same number of employers and
students and every employer will only hire one student.

## Input file format
A text file will be given as input. The first number will be the number of students and employers,
followed by the list of employers, the list of students and a matrix of ranking pairs (employer ranking,
student ranking). Employers correspond to the rows of this matrix and students correspond to columns.
For the simple example given on the first page, this input file will be as follows:

3\
Thales\
Canada Post\
Cisco\
Olivia\
Jackson\
Sophia\
1,1 2,1 3,2\
3,2 2,2 1,3\
1,3 3,3 2,1

## Output file format
Your program must simply produces as output a textfile. If the input file is called ABC.txt then the
output file must be called matches_ABC.txt that contains the list of match pairs employers – students keeping the same order for the company names as listed in the input file.

Match 0: Thales - Olivia\
Match 1: Canada Post - Jackson\
Match 2: Cisco - Sophia

You must follow this exact format in which matches are listed from 0 to n-1 (match i being the match
for company i). Company and student names are separated by a dash symbol (-). If you do not follow
this format, your solution will not be evaluated.
