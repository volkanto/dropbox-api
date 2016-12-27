FROM nginx:1.10.1

# Copy a configuration file from the current directory
ADD nginx.conf /etc/nginx/

# Append "daemon off;" to the beginning of the configuration
RUN echo "daemon off;" >> /etc/nginx/nginx.conf

RUN echo "Exposing 80 port..."

# Expose ports
EXPOSE 80

# Set the default command to execute
# when creating a new container
CMD ["service", "nginx", "start"]

RUN echo "Started nginx!"