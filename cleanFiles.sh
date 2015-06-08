cmd="git rm --cached --ignore-unmatch $1"
echo \'$cmd\'

git filter-branch --force --index-filter \
$cmd --prune-empty --tag-name-filter cat -- --all
