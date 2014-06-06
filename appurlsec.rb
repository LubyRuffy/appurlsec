#!/usr/bin/env ruby
require 'pp'
require 'pathname'
require 'domainatrix'
require 'uri'
require 'open-uri'

$basedir = Pathname.new(File.dirname(__FILE__)).realpath

def apk2java(apkpath, destdir=nil)
  jadx_cmd = "#{$basedir}/jadx/bin/jadx "
  jadx_cmd += "-d #{destdir} " if destdir
  jadx_cmd += apkpath
  puts jadx_cmd
  info = `#{jadx_cmd}`
  destdir ||= File.basename(apkpath).split('.')[0]
  destdir
end

def grephttp(destdir)
  #http:// and https://
  grep_cmd = "grep -irhEo \"http[s]?://[^'\\\"]*\" #{destdir}"
  puts grep_cmd
  info = `#{grep_cmd}`
  info.split(/[\r\n]/)
end

def host_of_url(url)
  begin
    url = 'http://'+url+'/' if !url.include?('http://') and !url.include?('https://')
    url = URI.encode(url) unless url.include? '%' #如果包含百分号%，说明已经编码过了
    uri = URI(url)
    uri.host
  rescue => e
    nil
  end
end

def hosts_of_urls(urls)
  hosts = urls.collect {|u| 
    host_of_url(u)
  }.uniq
  hosts
end

def remove_bigbrother(hosts)
  hosts = hosts.select {|h|
    h && !h.include?('.facebook.com') && !h.include?('.twitter.com') && !h.include?('.apache.org') && !h.include?('.amap') && !h.include?('.android.com') && !h.include?('.qq.com') && !h.include?('.google-analytics.com') && !h.include?('.baidu.com') 
  }
  hosts
end

def domains_of_hosts(hosts)
end

def ips_of_hosts(hosts)
end

def main(argv)
  destdir = apk2java(argv[0])
  urls = grephttp(destdir)
  #puts urls
  hosts = hosts_of_urls(urls)
  puts hosts
  domains = domains_of_hosts(hosts)
  ips = ips_of_hosts(hosts)
end

urls = grephttp(ARGV[0])
#puts urls
hosts = hosts_of_urls(urls)
puts remove_bigbrother(hosts)
#main(ARGV)
