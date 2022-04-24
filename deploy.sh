echo "frontend-public build"
cd frontend-public
npm run build

echo "frontend-public build"
cd ..
cd frontend-admin
npm run build

echo "+backend build jar packaging"
cd ..
cd backend
mvn clean install -Dmaven.test.skip=true
