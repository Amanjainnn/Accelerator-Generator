import os
import shutil

# Angular APP Name
angular_app_name = os.environ["appName"]

# Path where new angular app will be created
angular_path = os.environ["appDirectory"]

# Path of directory where angular files are kept
angular_files=os.environ["angularFiles"]

# Openapi jar path
openapi_jar_path=os.environ["openAPIJarPath"]

# default.yml path
default_yaml_path=os.environ["defaultYamlPath"]

# Command to generate new angular app
angular_app_cmd = "ng new "+str(angular_app_name)+" --defaults --skip-install"

#Ceate openAPI Angular APP at a path
angular_openapi_path=angular_path+"/"+str(angular_app_name)+"/src/app"
openapi_angular_cmd="java -jar "+str(openapi_jar_path)+" generate -i "+str(default_yaml_path)+" -g typescript-angular -o "+str(angular_openapi_path)


def replace_files(root_src_dir,root_dst_dir):
	"""
	Function to copy and replace files from Source to Destination.
	Any files or directories that already exist in the destination but not in the source will remain untouched.

	"""
	for src_dir, dirs, files in os.walk(root_src_dir):
	    dst_dir = src_dir.replace(root_src_dir, root_dst_dir, 1)
	    if not os.path.exists(dst_dir):
	        os.makedirs(dst_dir)
	    for file_ in files:
	        src_file = os.path.join(src_dir, file_)
	        dst_file = os.path.join(dst_dir, file_)
	        if os.path.exists(dst_file):
	            # in case of the src and dst are the same file
	            if os.path.samefile(src_file, dst_file):
	                continue
	            os.remove(dst_file)
	        shutil.copy(src_file, dst_dir)

def create_angular_app():
	"""
	Create Angular App and generate Opeen API Angular Apps

	"""
	os.chdir(angular_path)
	os.system(angular_app_cmd)
	os.system(openapi_angular_cmd)
	replace_files(angular_files, angular_openapi_path)

def automatic_flask_app_creator(app_name):
	"""
	generate Opeen API Flask Apps
	"""
	command="openapi-generator generate -i default.yml -g python-flask -o ./"+str(app_name)
	os.system(command)

# Call the driver function
create_angular_app()
