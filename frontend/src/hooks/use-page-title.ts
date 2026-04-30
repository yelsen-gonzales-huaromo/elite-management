import { useEffect } from 'react'
import { siteConfig } from '@/config/site'

export function usePageTitle(title?: string) {
  useEffect(() => {
    const fullTitle = title 
      ? `${title} | ${siteConfig.name}`
      : siteConfig.name
    
    document.title = fullTitle
  }, [title])
}
